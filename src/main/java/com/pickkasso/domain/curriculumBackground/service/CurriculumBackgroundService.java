package com.pickkasso.domain.curriculumBackground.service;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculumBackground.dao.CurriculumBackgroundRepository;
import com.pickkasso.domain.curriculumBackground.domain.CurriculumBackground;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumBackgroundService {
    private final CurriculumBackgroundRepository curriculumBackgroundRepository;

    public CurriculumBackground getCurriculumBackground(Curriculum curriculum) {
        return curriculumBackgroundRepository.findByCurriculum(curriculum);
    }

    public CurriculumBackground setCurriculumBackground(
            Curriculum curriculum, String backgroundLink) {
        CurriculumBackground curriculumBackground =
                CurriculumBackground.builder()
                        .curriculum(curriculum)
                        .backgrooundLink(backgroundLink)
                        .build();
        curriculumBackgroundRepository.save(curriculumBackground);
        return curriculumBackground;
    }
}
