package com.pickkasso.domain.curriculum.dto;

import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;

public class SelectedCurriculumResponse {
    private CurriculumResponse curriculumResponse;
    private RoundResponse roundResponse;
    private UserRoundResponse userRoundResponse;

    public SelectedCurriculumResponse(CurriculumResponse curriculumResponse, RoundResponse roundResponse, UserRoundResponse userRoundResponse){
        this.curriculumResponse = curriculumResponse;
        this.roundResponse = roundResponse;
        this.userRoundResponse = userRoundResponse;
    }
}
