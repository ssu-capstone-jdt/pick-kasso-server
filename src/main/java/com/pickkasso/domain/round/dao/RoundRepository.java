package com.pickkasso.domain.round.dao;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.round.domain.Round;

public interface RoundRepository extends JpaRepository<Round, Long> {
    Round findByCurriculum(Curriculum curriculum);
}
