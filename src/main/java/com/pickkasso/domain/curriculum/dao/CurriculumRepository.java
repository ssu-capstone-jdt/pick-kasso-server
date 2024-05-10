package com.pickkasso.domain.curriculum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickkasso.domain.curriculum.domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
