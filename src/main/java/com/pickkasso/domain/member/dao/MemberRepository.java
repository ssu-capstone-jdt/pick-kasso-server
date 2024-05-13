package com.pickkasso.domain.member.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.member.domain.OauthInfo;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthInfo(OauthInfo oauthInfo);
}
