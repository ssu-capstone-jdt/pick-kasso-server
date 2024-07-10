package com.pickkasso.domain.keyword.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.keyword.domain.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {}
