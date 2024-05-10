package com.pickkasso.infra.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    private String accessTokenSecret;
    private String refreshTokenSecret;
    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;
    private String issuer;

    public Long accessTokenExpirationMilliTime() {
        return accessTokenExpirationTime * 1000;
    }

    public Long refreshTokenExpirationMilliTime() {
        return refreshTokenExpirationTime * 1000;
    }
}
