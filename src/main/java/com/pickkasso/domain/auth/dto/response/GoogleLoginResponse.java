package com.pickkasso.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class GoogleLoginResponse {
    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String idToken;
}
