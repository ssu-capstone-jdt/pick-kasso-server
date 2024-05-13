package com.pickkasso.domain.curriculum.dto;

import java.util.List;

import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;

import lombok.Getter;

@Getter
public class SelectedCurriculumResponse {
    private CurriculumResponse curriculumResponse;
    private List<RoundResponse> roundResponse;
    private List<UserRoundResponse> userRoundResponse;

    public SelectedCurriculumResponse(
            CurriculumResponse curriculumResponse,
            List<RoundResponse> roundResponse,
            List<UserRoundResponse> userRoundResponse) {
        this.curriculumResponse = curriculumResponse;
        this.roundResponse = roundResponse;
        this.userRoundResponse = userRoundResponse;
    }
}
