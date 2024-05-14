package com.pickkasso.infra.config.storage;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StorageConfig {
    private final StorageProperties storageProperties;

    @Bean
    public AmazonS3 s3Client() {
        AWSCredentials credentials =
                new BasicAWSCredentials(
                        storageProperties.getAccessKey(), storageProperties.getSecretKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(storageProperties.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
