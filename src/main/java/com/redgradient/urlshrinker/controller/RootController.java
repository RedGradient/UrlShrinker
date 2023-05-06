package com.redgradient.urlshrinker.controller;

import com.redgradient.urlshrinker.dto.ShortUrlDto;
import com.redgradient.urlshrinker.dto.OriginUrlDto;
import com.redgradient.urlshrinker.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/api/short-url")
    public ShortUrlDto getShortUrl(@RequestBody @Validated OriginUrlDto originUrlDto) {
        return new ShortUrlDto(urlService.getShortUrl(originUrlDto));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        return urlService.getRedirect(shortUrl);
    }
}
