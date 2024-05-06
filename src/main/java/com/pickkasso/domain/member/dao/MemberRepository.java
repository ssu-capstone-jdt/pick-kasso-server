package com.pickkasso.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickkasso.domain.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {}
