package com.pickkasso.domain.userRound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.userRound.domain.UserRound;

public interface UserRoundRepository extends JpaRepository<UserRound, Long> {
    List<UserRound> findByMemberAndRound(Member member, Round round);
}
