package com.pickkasso.domain.member.application;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.auth.dao.RefreshTokenRepository;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.member.dto.request.WithdrawalRequest;
import com.pickkasso.domain.member.dto.response.MemberInfoResponse;
import com.pickkasso.domain.painting.service.PaintingService;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.domain.usercurriculum.service.UserCurriculumService;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberUtil memberUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PaintingService paintingService;
    private final UserRoundService userRoundService;
    private final UserCurriculumService userCurriculumService;

    public MemberInfoResponse getMemberInfo() {
        final Member member = memberUtil.getCurrentMember();
        return new MemberInfoResponse(member.getNickname(), member.getOauthInfo().getProfile());
    }

    public void withdrawal(WithdrawalRequest request) {
        final Member member = memberUtil.getCurrentMember();
        if (!request.getDeletePaintingState()) {
            paintingService.changeMemberIdPaintings(member);
        } else {
            paintingService.deleteAllMemberPaintings(member);
        }
        userRoundService.deleteUserCurriculums(member);
        userCurriculumService.deleteMemberCurriculum(member);
        refreshTokenRepository.deleteById(member.getId());
    }
}
