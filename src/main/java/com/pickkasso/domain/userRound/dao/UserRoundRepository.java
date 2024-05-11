package com.pickkasso.domain.userRound.dao;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;

import com.pickkasso.domain.userRound.domain.UserRound;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoundRepository extends JpaRepository<UserRound, Long> {
    UserRound findByMemberandCurriculum(Member member, Curriculum curriculum);
}
