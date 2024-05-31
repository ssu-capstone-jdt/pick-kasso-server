package com.pickkasso.domain.usercurriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import com.pickkasso.domain.usercurriculum.dto.response.DeleteUserCurriculumResponse;
import com.pickkasso.global.error.exception.CustomException;
import com.pickkasso.global.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCurriculumService {
    private final UserCurriculumRepository userCurriculumRepository;
    private final UserRoundRepository userRoundRepository;
    private final UserRoundService userRoundService;

    public List<UserCurriculum> findByMember(Member member) {
        return userCurriculumRepository.findByMember(member);
    }

    public UserCurriculum saveUserCurriculum(Member member, Curriculum curr) {
        UserCurriculum userCurriculum = UserCurriculum.createUserCurriculum(member, curr);

        for (Round round : curr.getRounds()) {
            UserRound userRound = userRoundService.setUserRound(member, round);
            round.getUserRounds().add(userRound);
            member.getUserRounds().add(userRound);
        }

        member.getUserCurriculums().add(userCurriculum);
        curr.getUserCurriculums().add(userCurriculum);

        return userCurriculum;
    }

    public DeleteUserCurriculumResponse deleteUserCurriculum(Member member, Curriculum curriculum) {

        UserCurriculum userCurriculum =
                userCurriculumRepository
                        .findByMemberAndCurriculum(member, curriculum)
                        .orElseThrow(() -> new CustomException(ErrorCode.USER_CURR_NOT_FOUND));

        //        //
        //        List<UserRound> userRounds =
        //                userRoundRepository.findByMemberAndRounds(member, curriculum.getRounds());
        //        //
        //
        //        for (UserRound userRound : userRounds) {
        //            member.getUserRounds().remove(userRound);
        //            userRoundRepository.delete(userRound);
        //        }

        for (Round round : curriculum.getRounds()) {
            UserRound userRound = userRoundRepository.findByMemberAndRound(member, round);
            member.getUserRounds().remove(userRound);
            userRoundRepository.delete(userRound);
        }

        member.getUserCurriculums().remove(userCurriculum);
        curriculum.getUserCurriculums().remove(userCurriculum);

        userCurriculumRepository.delete(userCurriculum);

        return new DeleteUserCurriculumResponse(
                member.getNickname(), curriculum.getCurriculumTitle());
    }

    public boolean isAlreadyDownloaded(Member member, Curriculum curriculum) {
        return userCurriculumRepository.existsByMemberAndCurriculum(member, curriculum);
    }

    public void deleteMemberCurriculum(Member member) {
        List<UserCurriculum> curriculumList = userCurriculumRepository.findByMember(member);
        for (UserCurriculum userCurriculum : curriculumList) {
            userCurriculumRepository.delete(userCurriculum);
        }
    }
}
