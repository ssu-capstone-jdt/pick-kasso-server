package com.pickkasso.domain.usercurriculum.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;

public interface UserCurriculumRepository extends JpaRepository<UserCurriculum, Long> {
    List<UserCurriculum> findByMember(Member member);

    Optional<UserCurriculum> findByMemberAndCurriculum(Member member, Curriculum curriculum);
}
