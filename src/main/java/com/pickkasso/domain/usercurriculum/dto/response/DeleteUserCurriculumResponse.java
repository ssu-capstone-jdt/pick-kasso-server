package com.pickkasso.domain.usercurriculum.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteUserCurriculumResponse {
    private String name;
    private String title;

    @Builder
    public DeleteUserCurriculumResponse(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
