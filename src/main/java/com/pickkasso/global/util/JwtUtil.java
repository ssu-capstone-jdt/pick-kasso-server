package com.pickkasso.global.util;

import static com.pickkasso.global.common.constants.SecurityConstants.TOKEN_ROLE_NAME;

import java.security.Key;
import java.util.Date;

import com.pickkasso.domain.auth.dto.AccessTokenDto;
import com.pickkasso.domain.auth.dto.RefreshTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Component;

import com.pickkasso.domain.member.domain.MemberRole;
import com.pickkasso.infra.config.jwt.JwtProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final JwtProperties jwtProperties;

    public String generateAccessToken(Long memberId, MemberRole memberRole) {
        Date issuedAt = new Date();
        Date expiredAt =
                new Date(issuedAt.getTime() + jwtProperties.accessTokenExpirationMilliTime());
        return buildAccessToken(memberId, memberRole, issuedAt, expiredAt);
    }

    public String generateRefreshToken(Long memberId) {
        Date issuedAt = new Date();
        Date expiredAt =
                new Date(issuedAt.getTime() + jwtProperties.refreshTokenExpirationMilliTime());
        return buildRefreshToken(memberId, issuedAt, expiredAt);
    }

    private String buildAccessToken(
            Long memberId, MemberRole memberRole, Date issuedAt, Date expiredAt) {
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setSubject(memberId.toString())
                .claim(TOKEN_ROLE_NAME, memberRole.name())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(getAccessTokenKey())
                .compact();
    }

    private String buildRefreshToken(Long memberId, Date issuedAt, Date expiredAt) {
        return Jwts.builder()
                .setIssuer(jwtProperties.getIssuer())
                .setSubject(memberId.toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(getRefreshTokenKey())
                .compact();
    }

    private Key getAccessTokenKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getAccessTokenSecret().getBytes());
    }

    private Key getRefreshTokenKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getRefreshTokenSecret().getBytes());
    }

    public AccessTokenDto parseAccessToken(String token) throws ExpiredJwtException {
        try {
            Jws<Claims> claims = getClaims(token, getAccessTokenKey());

            return new AccessTokenDto(
                    Long.parseLong(claims.getBody().getSubject()),
                    MemberRole.valueOf(claims.getBody().get(TOKEN_ROLE_NAME, String.class)),
                    token);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    private Jws<Claims> getClaims(String token, Key key) {
        return Jwts.parserBuilder()
                .requireIssuer(jwtProperties.getIssuer())
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public AccessTokenDto generateAccessTokenDto(Long memberId, MemberRole memberRole) {
        Date issuedAt = new Date();
        Date expiredAt =
                new Date(issuedAt.getTime() + jwtProperties.accessTokenExpirationMilliTime());
        String tokenValue = buildAccessToken(memberId, memberRole, issuedAt, expiredAt);
        return new AccessTokenDto(memberId, memberRole, tokenValue);
    }

    public RefreshTokenDto parseRefreshToken(String token) throws ExpiredJwtException {
        try {
            Jws<Claims> claims = getClaims(token, getRefreshTokenKey());

            return new RefreshTokenDto(
                    Long.parseLong(claims.getBody().getSubject()),
                    token,
                    jwtProperties.getRefreshTokenExpirationTime());
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }
}
