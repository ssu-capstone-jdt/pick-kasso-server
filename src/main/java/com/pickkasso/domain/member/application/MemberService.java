package com.pickkasso.domain.member.application;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.member.dto.response.MemberInfoResponse;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberUtil memberUtil;

    public MemberInfoResponse getMemberInfo() {
        final Member member = memberUtil.getCurrentMember();
        return new MemberInfoResponse(member.getNickname(), member.getOauthInfo().getProfile());
    }
}
