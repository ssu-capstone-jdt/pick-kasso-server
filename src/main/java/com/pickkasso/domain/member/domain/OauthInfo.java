package com.pickkasso.domain.member.domain;

import jakarta.persistence.Embeddable;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class OauthInfo {
    private String oauthId;
    private String oauthProvider;
    private String oauthEmail;
    private String profile;

    @Builder
    public OauthInfo(String oauthId, String oauthProvider, String oauthEmail, String profile) {
        this.oauthId = oauthId;
        this.oauthProvider = oauthProvider;
        this.oauthEmail = oauthEmail;
        this.profile = profile;
    }

    public static OauthInfo createOauthInfo(OidcUser user) {
        return OauthInfo.builder()
                .oauthId(user.getSubject())
                .oauthProvider(user.getIssuer().toString())
                .oauthEmail(user.getEmail())
                .profile(user.getAttributes().get("picture").toString())
                .build();
    }
}
