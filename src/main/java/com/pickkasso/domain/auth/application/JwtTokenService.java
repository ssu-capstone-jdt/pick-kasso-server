package com.pickkasso.domain.auth.application;

import static com.pickkasso.global.common.constants.SecurityConstants.TOKEN_ROLE_NAME;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.auth.dao.RefreshTokenRepository;
import com.pickkasso.domain.auth.domain.RefreshToken;
import com.pickkasso.domain.auth.dto.AccessTokenDto;
import com.pickkasso.domain.auth.dto.RefreshTokenDto;
import com.pickkasso.domain.member.domain.MemberRole;
import com.pickkasso.global.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public String createAccessToken(Long memberId, MemberRole role) {
        return jwtUtil.generateAccessToken(memberId, role);
    }

    public String createRefreshToken(Long memberId) {
        String token = jwtUtil.generateRefreshToken(memberId);
        RefreshToken refreshToken = RefreshToken.builder().memberId(memberId).token(token).build();
        refreshTokenRepository.save(refreshToken);
        return token;
    }

    public AccessTokenDto createAccessTokenDto(Long memberId, MemberRole memberRole) {
        return jwtUtil.generateAccessTokenDto(memberId, memberRole);
    }

    public AccessTokenDto retrieveOrReissueAccessToken(String accessTokenValue) {
        try {
            return jwtUtil.parseAccessToken(accessTokenValue);
        } catch (ExpiredJwtException e) {
            Long memberId = Long.parseLong(e.getClaims().getSubject());
            MemberRole memberRole =
                    MemberRole.valueOf(e.getClaims().get(TOKEN_ROLE_NAME, String.class));
            return createAccessTokenDto(memberId, memberRole);
        } catch (Exception e) {
            return null;
        }
    }

    public RefreshTokenDto retrieveRefreshToken(String refreshTokenValue) {
        return parseRefreshToken(refreshTokenValue);
    }

    private RefreshTokenDto parseRefreshToken(String refreshTokenValue) {
        try {
            return jwtUtil.parseRefreshToken(refreshTokenValue);
        } catch (Exception e) {
            return null;
        }
    }
}
