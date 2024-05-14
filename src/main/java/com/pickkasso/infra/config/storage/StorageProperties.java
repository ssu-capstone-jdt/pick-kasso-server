package com.pickkasso.infra.config.storage;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;
    private String cloudFrontDomain;
}
