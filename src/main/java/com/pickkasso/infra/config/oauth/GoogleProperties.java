package com.pickkasso.infra.config.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "oauth.google.client")
@Getter
@Setter
public class GoogleProperties {
    private String id;
    private String secret;
    private String redirectUri;
}
