package com.pickkasso.domain.curriculum.dto;

import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;

import java.util.List;

public class SelectedCurriculumResponse {
    private CurriculumResponse curriculumResponse;
    private List<RoundResponse> roundResponse;
    private List<UserRoundResponse> userRoundResponse;

    public SelectedCurriculumResponse(CurriculumResponse curriculumResponse, List<RoundResponse> roundResponse, List<UserRoundResponse> userRoundResponse){
        this.curriculumResponse = curriculumResponse;
        this.roundResponse = roundResponse;
        this.userRoundResponse = userRoundResponse;
    }

}
