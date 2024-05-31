package com.pickkasso.domain.painting.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickkasso.domain.auth.dao.GoogleRefreshTokenRepository;
import com.pickkasso.domain.auth.domain.GoogleRefreshToken;
import com.pickkasso.domain.auth.dto.response.GoogleAccessTokenResponse;
import com.pickkasso.domain.painting.dto.response.GoogleDriveFileResponse;
import com.pickkasso.global.common.constants.SecurityConstants;
import com.pickkasso.global.error.exception.CustomException;
import com.pickkasso.global.error.exception.ErrorCode;
import com.pickkasso.infra.config.feign.GoogleApiClient;
import com.pickkasso.infra.config.feign.GoogleDriveApiClient;
import com.pickkasso.infra.config.oauth.GoogleProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GoogleDriveService {
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String IMAGE_CONTENT_TYPE = "image/jpeg";

    private final GoogleDriveApiClient googleDriveApiClient;
    private final GoogleRefreshTokenRepository refreshTokenRepository;
    private final GoogleProperties properties;
    private final GoogleApiClient googleApiClient;

    public void uploadToGoogleDrive(Long memberId, byte[] fileContent) {
        GoogleAccessTokenResponse response = getAccessToken(memberId);
        String accessToken = setBearer(response.getAccessToken());
        GoogleDriveFileResponse uploadResponse =
                googleDriveApiClient.uploadFile(accessToken, IMAGE_CONTENT_TYPE, fileContent);
        log.info(uploadResponse.getId());
        log.info(uploadResponse.getName());
    }

    public String setBearer(String accessToken) {
        return SecurityConstants.TOKEN_PREFIX.concat(accessToken);
    }

    private GoogleAccessTokenResponse getAccessToken(Long memberId) {
        GoogleRefreshToken refreshToken =
                refreshTokenRepository
                        .findById(memberId)
                        .orElseThrow(() -> new CustomException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        return googleApiClient.getAccessToken(
                refreshToken.getToken(), properties.getId(), properties.getSecret(), REFRESH_TOKEN);
    }
}
