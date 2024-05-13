package com.pickkasso.domain.member.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.common.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 10)
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Embedded private OauthInfo oauthInfo;

    @Column private String profilelink;

    @Builder
    private Member(String nickname, OauthInfo oauthInfo, MemberRole role) {
        this.nickname = nickname;
        this.role = role;
        this.oauthInfo = oauthInfo;
        this.profilelink = null;
    }

    public static Member createMember(OauthInfo oauthInfo, String nickname) {
        return Member.builder()
                .nickname(nickname)
                .role(MemberRole.USER)
                .oauthInfo(oauthInfo)
                .build();
    }
}
