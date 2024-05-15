package com.pickkasso.domain.curriculum.dto.response;

import com.pickkasso.domain.curriculum.domain.Curriculum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCurriculumResponse {
    private Long id;
    private String curriculumTitle;
    private String curriculumInfo;
    private String curriculumExplanation;
    private int curriculumRoundCount;
    private String curriculumDifficulty;

    public AddCurriculumResponse(Curriculum curriculum) {
        this.id = curriculum.getId();
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
        this.curriculumExplanation = curriculum.getCurriculumExplanation();
        this.curriculumRoundCount = curriculum.getCurriculumRoundCount();
        this.curriculumDifficulty = curriculum.getCurriculumDifficulty();
    }
}
