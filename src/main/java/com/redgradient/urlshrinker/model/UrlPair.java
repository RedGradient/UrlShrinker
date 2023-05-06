package com.redgradient.urlshrinker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Getter
@NoArgsConstructor
public class UrlPair {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String shortUrl;
    @NotBlank
    @Column(unique = true)
    private String originUrl;
    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private Date createdAt;

    public UrlPair(String shortUrl, String originUrl) {
        this.shortUrl = shortUrl;
        this.originUrl = originUrl;
    }
}

