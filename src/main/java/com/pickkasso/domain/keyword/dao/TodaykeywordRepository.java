package com.pickkasso.domain.keyword.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.keyword.domain.TodayKeyword;

public interface TodaykeywordRepository extends JpaRepository<TodayKeyword, Long> {

    TodayKeyword findByDate(String date);

    boolean existsByDate(String date);
}
