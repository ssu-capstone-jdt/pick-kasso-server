package com.pickkasso.domain.curriculumBackground.dao;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculumBackground.domain.CurriculumBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumBackgroundRepository extends JpaRepository<CurriculumBackground, Long> {
    CurriculumBackground findByCurriculum(Curriculum curriculum);
}
