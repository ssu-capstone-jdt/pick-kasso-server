package com.pickkasso.domain.member.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pickkasso.domain.member.application.MemberService;
import com.pickkasso.domain.member.dto.response.MemberInfoResponse;
import com.pickkasso.global.util.CookieUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    private final CookieUtil cookieUtil;

    @GetMapping
    public MemberInfoResponse getMemberProfile() {
        return memberService.getMemberInfo();
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<Void> memberWithdrawal() {
        memberService.withdrawal();
        return ResponseEntity.status(HttpStatus.OK)
                .headers(cookieUtil.deleteTokenCookies())
                .build();
    }
}
