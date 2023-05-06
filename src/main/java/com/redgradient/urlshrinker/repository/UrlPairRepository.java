package com.redgradient.urlshrinker.repository;

import com.redgradient.urlshrinker.model.UrlPair;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UrlPairRepository extends CrudRepository<UrlPair, Long> {
    Optional<UrlPair> findByOriginUrl(String originUrl);
    Optional<UrlPair> findByShortUrl(String shortUrl);
}
