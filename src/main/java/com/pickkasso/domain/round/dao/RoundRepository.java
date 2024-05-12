package com.pickkasso.domain.round.dao;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.round.domain.Round;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round, Long> {
    List<Round> findByCurriculum(Curriculum curriculum);
    Round findByCurriculumAndOrder(Curriculum curriculum, int order);
}
