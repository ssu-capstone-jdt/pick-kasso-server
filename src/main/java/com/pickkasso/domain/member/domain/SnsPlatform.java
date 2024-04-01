package com.pickkasso.domain.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SnsPlatform {
    NAVER("NAVER"),
    KAKAO("KAKAO");

    private final String value;
}
