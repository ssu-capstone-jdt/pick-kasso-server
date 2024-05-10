package com.pickkasso.domain.auth.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickkasso.domain.auth.application.AuthService;
import com.pickkasso.domain.auth.dto.request.AuthCodeRequest;
import com.pickkasso.domain.auth.dto.response.TokenPairResponse;
import com.pickkasso.global.util.CookieUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CookieUtil cookieUtil;

    @PostMapping("/code")
    public ResponseEntity<TokenPairResponse> memberOauthRegister(
            @RequestBody AuthCodeRequest request) {
        TokenPairResponse tokenPairResponse = authService.socialLogin(request.getCode());
        String accessToken = tokenPairResponse.getAccessToken();
        String refreshToken = tokenPairResponse.getRefreshToken();
        HttpHeaders httpHeaders = cookieUtil.generateTokenCookies(accessToken, refreshToken);

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(tokenPairResponse);
    }
}
