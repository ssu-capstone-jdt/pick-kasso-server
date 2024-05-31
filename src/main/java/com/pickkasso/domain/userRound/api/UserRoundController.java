package com.pickkasso.domain.userRound.api;

import org.springframework.web.bind.annotation.*;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.userRound.dto.UserRoundCompleteResponse;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRoundController {
    private final MemberUtil memberUtil;
    private final UserRoundService userRoundService;

    @GetMapping("/curriculums/rounds/{id}")
    public UserRoundCompleteResponse isUploadSuccessful(@PathVariable long id) {
        Member member = memberUtil.getCurrentMember();
        return userRoundService.isUploadSuccessful(member, id);
    }
}
