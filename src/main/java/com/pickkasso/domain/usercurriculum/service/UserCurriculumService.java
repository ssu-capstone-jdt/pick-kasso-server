package com.pickkasso.domain.usercurriculum.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCurriculumService {
    private final UserCurriculumRepository userCurriculumRepository;

    public List<UserCurriculum> findByMember(Member member) {
        return userCurriculumRepository.findByMember(member);
    }

    public UserCurriculum save(UserCurriculum userCurriculum) {
        return userCurriculumRepository.save(userCurriculum);
    }

    public UserCurriculum getUserCurriculum(Member member, Curriculum curriculum) {
        UserCurriculum userCurriculum = new UserCurriculum(member, curriculum);
        return userCurriculum;
    }
}
