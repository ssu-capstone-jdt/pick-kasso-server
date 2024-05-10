package com.pickkasso.domain.curriculum.dto;

////test
//import java.util.Objects;

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

    // private List<RoundResponse> rounds;

    public CurriculumResponse(Curriculum curriculum) {
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
        this.curriculumExplanation = curriculum.getCurriculumExplanation();
        this.curriculumBackground = curriculum.getCurriculumBackground();
        this.curriculumRoundCount = curriculum.getCurriculumRoundCount();
        this.curriculumDifficulty = curriculum.getCurriculumDifficulty();
    }

//    // test
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        CurriculumResponse other = (CurriculumResponse) obj;
//        return Objects.equals(this.curriculumTitle, other.curriculumTitle)
//                && Objects.equals(this.curriculumInfo, other.curriculumInfo)
//                && Objects.equals(this.curriculumExplanation, other.curriculumExplanation)
//                && Objects.equals(this.curriculumBackground, other.curriculumBackground)
//                && this.curriculumRoundCount == other.curriculumRoundCount
//                && Objects.equals(this.curriculumDifficulty, other.curriculumDifficulty);
//    }
}
