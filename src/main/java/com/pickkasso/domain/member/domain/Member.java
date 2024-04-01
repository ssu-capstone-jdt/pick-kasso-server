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

    @Column(name = "sns_id")
    private Long snsId;

    @Column(length = 10)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private SnsPlatform snsPlatform;

    @Builder
    private Member(Long snsId, String nickname, SnsPlatform snsPlatform) {
        this.snsId = snsId;
        this.nickname = nickname;
        this.snsPlatform = snsPlatform;
    }

    public static Member createMember(Long snsId, String nickname, SnsPlatform snsPlatform) {
        return Member.builder().snsId(snsId).nickname(nickname).snsPlatform(snsPlatform).build();
    }
}
