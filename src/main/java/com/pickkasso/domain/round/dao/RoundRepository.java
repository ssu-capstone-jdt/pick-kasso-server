package com.pickkasso.domain.round.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.round.domain.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {
    List<Round> findByCurriculum(Curriculum curriculum);
}
