package com.pickkasso.domain.usercurriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCurriculumService {
    private final UserCurriculumRepository userCurriculumRepository;
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
}
