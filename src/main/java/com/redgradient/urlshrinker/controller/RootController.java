package com.redgradient.urlshrinker.controller;

import com.redgradient.urlshrinker.dto.ShortUrlDto;
import com.redgradient.urlshrinker.dto.OriginUrlDto;
import com.redgradient.urlshrinker.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @Autowired
    private UrlService urlService;

    @Operation(summary = "Get short url")
    @ApiResponse(responseCode = "200", description = "Short url",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = ShortUrlDto.class)))
    @PostMapping("/api/short-url")
    public ShortUrlDto getShortUrl(@RequestBody @Validated OriginUrlDto originUrlDto) {
        return new ShortUrlDto(urlService.getShortUrl(originUrlDto));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        return urlService.getRedirect(shortUrl);
    }
}
