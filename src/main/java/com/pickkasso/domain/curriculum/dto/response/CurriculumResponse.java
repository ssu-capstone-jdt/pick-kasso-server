package com.pickkasso.domain.curriculum.dto.response;

import com.pickkasso.domain.curriculum.domain.Curriculum;

import lombok.Getter;

@Getter
public class CurriculumResponse {
    private String curriculumTitle;
    private String curriculumInfo;
    private String curriculumExplanation;
    private String curriculumBackground;
    private int curriculumRoundCount;
    private String curriculumDifficulty;

    public CurriculumResponse(Curriculum curriculum) {
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
        this.curriculumExplanation = curriculum.getCurriculumExplanation();
        this.curriculumRoundCount = curriculum.getCurriculumRoundCount();
        this.curriculumDifficulty = curriculum.getCurriculumDifficulty();
        this.curriculumBackground = curriculum.getCurriculumBackgrounds().getBackgroundLink();
    }
}
