package com.pickkasso.domain.usercurriculum.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DownloadCurriculumResponse {
    private final String username;
    private final String curriculumTitle;

    @Builder
    public DownloadCurriculumResponse(String username, String title) {
        this.username = username;
        this.curriculumTitle = title;
    }
}
