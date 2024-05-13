package com.pickkasso.global.security;

import com.pickkasso.domain.auth.application.JwtTokenService;
import com.pickkasso.domain.auth.dto.AccessTokenDto;
import com.pickkasso.domain.auth.dto.RefreshTokenDto;
import com.pickkasso.domain.member.domain.MemberRole;
import com.pickkasso.global.util.CookieUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

import static com.pickkasso.global.common.constants.SecurityConstants.*;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessTokenValue = getAccessTokenFromCookie(request);
        String refreshTokenValue = getRefreshTokenFromCookie(request);

        if (accessTokenValue == null || refreshTokenValue == null) {
            filterChain.doFilter(request, response);
            return;
        }

        AccessTokenDto accessTokenDto = jwtTokenService.retrieveOrReissueAccessToken(accessTokenValue);

        if (accessTokenDto != null) {
            setAuthenticationToContext(accessTokenDto.getMemberId(), accessTokenDto.getMemberRole());
            filterChain.doFilter(request, response);
            return;
        }

        // RT 유효하면 파싱
        RefreshTokenDto refreshTokenDto = jwtTokenService.retrieveRefreshToken(refreshTokenValue);

        // AT가 만료되었고, RT가 유효하면 AT, RT 재발급
        if (refreshTokenDto != null) {
            String accessToken = jwtTokenService.createAccessToken(refreshTokenDto.getMemberId(), MemberRole.USER);
            String refreshToken = jwtTokenService.createRefreshToken(refreshTokenDto.getMemberId());

            HttpHeaders httpHeaders =
                    cookieUtil.generateTokenCookies(
                            accessToken, refreshToken);
            response.addHeader(
                    HttpHeaders.SET_COOKIE, httpHeaders.getFirst(ACCESS_TOKEN_COOKIE_NAME));
            response.addHeader(
                    HttpHeaders.SET_COOKIE, httpHeaders.getFirst(REFRESH_TOKEN_COOKIE_NAME));

            setAuthenticationToContext(refreshTokenDto.getMemberId(), MemberRole.USER);
            filterChain.doFilter(request, response);
            return;

        }
        // AT, RT가 모두 만료된 경우 실패
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationToContext(Long memberId, MemberRole memberRole) {
        UserDetails userDetails = User.withUsername(memberId.toString())
                .authorities(memberRole.toString())
                .password("")
                .build();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private String getAccessTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, ACCESS_TOKEN_COOKIE_NAME);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, REFRESH_TOKEN_COOKIE_NAME);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

}
