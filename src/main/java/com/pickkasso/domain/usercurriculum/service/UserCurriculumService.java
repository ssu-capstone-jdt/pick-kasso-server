package com.pickkasso.domain.usercurriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import com.pickkasso.domain.usercurriculum.dto.response.DeleteUserCurriculumResponse;
import com.pickkasso.global.error.exception.CustomException;
import com.pickkasso.global.error.exception.ErrorCode;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCurriculumService {
    private final UserCurriculumRepository userCurriculumRepository;
    private final UserRoundRepository userRoundRepository;
    private final MemberUtil memberUtil;

    public List<UserCurriculum> findByMember(Member member) {
        return userCurriculumRepository.findByMember(member);
    }

    public UserCurriculum saveUserCurriculum(Curriculum curr) {
        Member member = memberUtil.getCurrentMember();
        UserCurriculum userCurriculum = UserCurriculum.createUserCurriculum(member, curr);

        for (Round round : curr.getRounds()) {
            UserRound userRound = UserRound.createUserRound(member, round);
            round.getUserRounds().add(userRound);
            member.getUserRounds().add(userRound);
        }

        member.getUserCurriculums().add(userCurriculum);
        curr.getUserCurriculums().add(userCurriculum);

        return userCurriculum;
    }

    public DeleteUserCurriculumResponse deleteUserCurriculum(Curriculum curriculum) {
        Member member = memberUtil.getCurrentMember();

        UserCurriculum userCurriculum =
                userCurriculumRepository
                        .findByMemberAndCurriculum(member, curriculum)
                        .orElseThrow(() -> new CustomException(ErrorCode.USER_CURR_NOT_FOUND));

        List<UserRound> userRounds =
                userRoundRepository.findByMemberAndRoundIn(member, curriculum.getRounds());
        for (UserRound userRound : userRounds) {
            member.getUserRounds().remove(userRound);
            userRoundRepository.delete(userRound);
        }

        member.getUserCurriculums().remove(userCurriculum);
        curriculum.getUserCurriculums().remove(userCurriculum);

        userCurriculumRepository.delete(userCurriculum);

        return new DeleteUserCurriculumResponse(
                member.getNickname(), curriculum.getCurriculumTitle());
    }
}
