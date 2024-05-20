package com.pickkasso.global.util;

import static com.pickkasso.global.common.constants.SecurityConstants.ACCESS_TOKEN_COOKIE_NAME;
import static com.pickkasso.global.common.constants.SecurityConstants.REFRESH_TOKEN_COOKIE_NAME;

import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    public HttpHeaders generateTokenCookies(String accessToken, String refreshToken) {

        String sameSite = Cookie.SameSite.NONE.attributeValue();

        ResponseCookie accessTokenCookie =
                ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, accessToken)
                        .path("/")
                        .secure(true)
                        .sameSite(sameSite)
                        .httpOnly(true)
                        .build();

        ResponseCookie refreshTokenCookie =
                ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                        .path("/")
                        .secure(true)
                        .sameSite(sameSite)
                        .httpOnly(true)
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return headers;
    }

    public HttpHeaders deleteTokenCookies() {

        String sameSite = Cookie.SameSite.NONE.attributeValue();

        ResponseCookie accessTokenCookie =
                ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, "")
                        .path("/")
                        .maxAge(0)
                        .secure(true)
                        .sameSite(sameSite)
                        .httpOnly(false)
                        .build();

        ResponseCookie refreshTokenCookie =
                ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, "")
                        .path("/")
                        .maxAge(0)
                        .secure(true)
                        .sameSite(sameSite)
                        .httpOnly(false)
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());

        return headers;
    }
}
