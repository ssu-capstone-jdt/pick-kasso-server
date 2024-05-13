package com.pickkasso.domain.member.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickkasso.domain.member.application.MemberService;
import com.pickkasso.domain.member.dto.response.MemberInfoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public MemberInfoResponse getMemberProfile() {
        return memberService.getMemberInfo();
    }
}
