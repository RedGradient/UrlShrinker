package com.redgradient.urlshrinker.service;

import com.redgradient.urlshrinker.UrlNotFoundException;
import com.redgradient.urlshrinker.dto.OriginUrlDto;
import com.redgradient.urlshrinker.model.UrlPair;
import com.redgradient.urlshrinker.repository.UrlPairRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlPairRepository urlPairRepository;

    @Autowired
    private HttpServletRequest request;

    public String getShortUrl(OriginUrlDto originUrlDto) {
        var scheme = request.getScheme();
        var serverName = request.getServerName();
        var serverPort = request.getServerPort();
        var builder = UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(serverName)
                .port(serverPort);

        var urlPairOptional = urlPairRepository.findByOriginUrl(originUrlDto.getUrl());
        if (urlPairOptional.isPresent()) {
            var shortUrl = urlPairOptional.get().getShortUrl();
            return builder.path(shortUrl).build().toString();
        }

        var length = 10;
        var characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var sb = new StringBuilder(length);
        var random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        var shortUrl = sb.toString();

        var urlPair = new UrlPair(shortUrl, originUrlDto.getUrl());
        urlPairRepository.save(urlPair);

        return builder.path(shortUrl).build().toString();
    }

    public RedirectView getRedirect(String shortUrl) {
        var urlPairOptional = urlPairRepository.findByShortUrl(shortUrl);
        if (urlPairOptional.isEmpty()) {
            throw new UrlNotFoundException(shortUrl);
        }
        return new RedirectView(urlPairOptional.get().getOriginUrl());
    }
}
