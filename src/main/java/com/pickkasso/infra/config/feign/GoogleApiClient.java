package com.pickkasso.infra.config.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickkasso.domain.auth.dto.response.GoogleTokenResponse;
import com.pickkasso.global.config.feign.GoogleFeignConfig;

@FeignClient(
        name = "googleApiClient",
        url = "https://oauth2.googleapis.com",
        configuration = GoogleFeignConfig.class)
public interface GoogleApiClient {
    @PostMapping(value = "/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    GoogleTokenResponse getToken(
            @RequestParam("code") String code,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam("grant_type") String grantType);
}
