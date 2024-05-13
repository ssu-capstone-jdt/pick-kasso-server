package com.pickkasso.global.util;

import org.springframework.stereotype.Component;

import com.pickkasso.domain.member.dao.MemberRepository;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.global.error.exception.CustomException;
import com.pickkasso.global.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final SecurityUtil securityUtil;
    private final MemberRepository memberRepository;

    public Member getCurrentMember() {
        return memberRepository
                .findById(securityUtil.getCurrentMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
