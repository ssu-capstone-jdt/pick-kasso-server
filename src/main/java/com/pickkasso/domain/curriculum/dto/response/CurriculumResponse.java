package com.pickkasso.domain.curriculum.dto.response;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.domain.CurriculumDifficulty;

import lombok.Getter;

@Getter
public class CurriculumResponse {
    private Long curriculumId;
    private String curriculumTitle;
    private String curriculumInfo;
    private String curriculumExplanation;
    private String curriculumPainting;
    private int curriculumRoundCount;
    private CurriculumDifficulty curriculumDifficulty;

    public CurriculumResponse(Curriculum curriculum) {
        this.curriculumId = curriculum.getId();
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
        this.curriculumExplanation = curriculum.getCurriculumExplanation();
        this.curriculumRoundCount = curriculum.getCurriculumRoundCount();
        this.curriculumDifficulty = curriculum.getCurriculumDifficulty();
        this.curriculumPainting = curriculum.getCurriculumPainting();
    }
}
