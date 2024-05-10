package com.pickkasso.domain.auth.application;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.auth.dao.RefreshTokenRepository;
import com.pickkasso.domain.auth.domain.RefreshToken;
import com.pickkasso.domain.member.domain.MemberRole;
import com.pickkasso.global.util.JwtUtil;

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
}
