package com.pickkasso.domain.curriculumBackground.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculumBackground.dao.CurriculumBackgroundRepository;
import com.pickkasso.domain.curriculumBackground.domain.CurriculumBackground;
import org.springframework.stereotype.Service;

@Service
public class CurriculumBackgroundService {
    private CurriculumBackgroundRepository curriculumBackgroundRepository;
    public CurriculumBackground getCurriculumBackground(Curriculum curriculum){
        return curriculumBackgroundRepository.findByCurriculum(curriculum);
    }

    public void setCurriculumBackground(Curriculum curriculum, String backgroundLink){
        CurriculumBackground curriculumBackground = new CurriculumBackground(curriculum, backgroundLink);
        curriculumBackgroundRepository.save(curriculumBackground);
    }
}
