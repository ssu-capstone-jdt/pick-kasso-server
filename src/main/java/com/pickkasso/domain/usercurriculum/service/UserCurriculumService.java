package com.pickkasso.domain.usercurriculum.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCurriculumService {
    private final UserCurriculumRepository userCurriculumRepository;
    public List<UserCurriculum> findByMember(Member member){
        return userCurriculumRepository.findByMember(member);
    }

    public void save(UserCurriculum userCurriculum){
        userCurriculumRepository.save(userCurriculum);
    }

    public UserCurriculum getUserCurriculum(Member member, Curriculum curriculum){
        UserCurriculum userCurriculum = new UserCurriculum(member, curriculum);
        return userCurriculum;
    }
}
