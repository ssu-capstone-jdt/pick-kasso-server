package com.pickkasso.domain.auth.application;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickkasso.domain.auth.dto.response.GoogleLoginResponse;
import com.pickkasso.domain.auth.dto.response.TokenPairResponse;
import com.pickkasso.domain.member.dao.MemberRepository;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.member.domain.OauthInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final IdTokenVerifier idTokenVerifier;
    private final MemberRepository memberRepository;
    private final GoogleTokenService googleTokenService;
    private final JwtTokenService jwtTokenService;

    public TokenPairResponse socialLogin(String authcode) {
        GoogleLoginResponse response = googleTokenService.getGoogleTokenResponse(authcode);
        OidcUser oidcUser = idTokenVerifier.getOidcUser(response.getIdToken());
        OauthInfo oauthInfo = OauthInfo.createOauthInfo(oidcUser);

        Member member = getMemberByOidcInfo(oidcUser, oauthInfo);
        googleTokenService.saveGoogleRefreshToken(member.getId(), response.getRefreshToken());

        setAuthenticationToken(member);

        return getLoginResponse(member);
    }

    private Member getMemberByOidcInfo(OidcUser oidcUser, OauthInfo oauthInfo) {
        return memberRepository
                .findByOauthInfo(oauthInfo)
                .orElseGet(() -> saveMember(oauthInfo, getUserSocialName(oidcUser)));
    }

    private String getUserSocialName(OidcUser oidcUser) {
        return oidcUser.getClaim("name");
    }

    private TokenPairResponse getLoginResponse(Member member) {
        String accessToken = jwtTokenService.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenService.createRefreshToken(member.getId());
        return new TokenPairResponse(accessToken, refreshToken);
    }

    private void setAuthenticationToken(Member member) {
        UserDetails userDetails =
                User.withUsername(member.getId().toString())
                        .authorities(member.getRole().toString())
                        .password("")
                        .build();
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private Member saveMember(OauthInfo oauthInfo, String nickname) {
        return memberRepository.save(Member.createMember(oauthInfo, nickname));
    }
}
