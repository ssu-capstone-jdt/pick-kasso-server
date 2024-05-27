package com.pickkasso.infra.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pickkasso.domain.painting.dto.response.GoogleDriveFileResponse;
import com.pickkasso.global.config.feign.GoogleFeignConfig;

@FeignClient(
        name = "googleDriveApiClient",
        url = "https://www.googleapis.com/upload/drive/v3",
        configuration = GoogleFeignConfig.class)
public interface GoogleDriveApiClient {
    @PostMapping(
            value = "/files?uploadType=media",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    GoogleDriveFileResponse uploadFile(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Content-Type") String contentType,
            @RequestBody byte[] fileContent);
}
