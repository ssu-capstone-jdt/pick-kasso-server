package com.pickkasso.domain.curriculum.dto.response;

import java.util.List;

import com.pickkasso.domain.round.dto.DownloadRoundResponse;

import lombok.Getter;

@Getter
public class SelectedCurriculumResponse {
    private CurriculumResponse curriculumResponse;
    private List<DownloadRoundResponse> downloadRoundResponse;

    public SelectedCurriculumResponse(
            CurriculumResponse curriculumResponse,
            List<DownloadRoundResponse> downloadRoundResponse) {
        this.curriculumResponse = curriculumResponse;
        this.downloadRoundResponse = downloadRoundResponse;
    }
}
