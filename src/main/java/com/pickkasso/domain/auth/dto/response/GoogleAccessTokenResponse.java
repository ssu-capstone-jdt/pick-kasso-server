package com.pickkasso.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleAccessTokenResponse {
    private String accessToken;
    private String tokenType;
    private String expireIn;
    private String refreshToken;
    private String scope;
}
