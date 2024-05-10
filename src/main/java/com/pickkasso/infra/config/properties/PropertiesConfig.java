package com.pickkasso.infra.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.pickkasso.infra.config.jwt.JwtProperties;
import com.pickkasso.infra.config.oauth.GoogleProperties;

@EnableConfigurationProperties({GoogleProperties.class, JwtProperties.class})
@Configuration
public class PropertiesConfig {}
