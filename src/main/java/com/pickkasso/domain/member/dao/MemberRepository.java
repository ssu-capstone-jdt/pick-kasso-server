package com.pickkasso.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
