package com.pickkasso.domain.curriculum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickkasso.domain.curriculum.domain.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {}
