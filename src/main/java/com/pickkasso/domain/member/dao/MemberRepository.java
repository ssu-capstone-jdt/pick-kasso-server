package com.pickkasso.domain.member.dao;

import java.lang.reflect.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
