package com.pickkasso.domain.auth.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickkasso.domain.auth.dao.GoogleRefreshTokenRepository;
import com.pickkasso.domain.auth.domain.GoogleRefreshToken;
import com.pickkasso.domain.auth.dto.response.GoogleTokenResponse;
import com.pickkasso.infra.config.feign.GoogleApiClient;
import com.pickkasso.infra.config.oauth.GoogleProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GoogleTokenService {
    private static final String AUTHORIZATION_CODE = "authorization_code";

    private final GoogleRefreshTokenRepository refreshTokenRepository;
    private final GoogleApiClient googleApiClient;
    private final GoogleProperties properties;

    public GoogleTokenResponse getGoogleTokenResponse(String authcode) {
        return googleApiClient.getToken(
                authcode,
                properties.getId(),
                properties.getSecret(),
                properties.getRedirectUri(),
                AUTHORIZATION_CODE);
    }

    public void saveGoogleRefreshToken(Long memberId, String newToken) {
        Optional<GoogleRefreshToken> tokenOptional = refreshTokenRepository.findById(memberId);
        if (tokenOptional.isPresent()) {
            GoogleRefreshToken savedToken = tokenOptional.get();
            savedToken.setToken(newToken);
            return;
        }
        GoogleRefreshToken newGoogleRefreshToken =
                GoogleRefreshToken.builder().memberId(memberId).token(newToken).build();
        refreshTokenRepository.save(newGoogleRefreshToken);
    }
}
