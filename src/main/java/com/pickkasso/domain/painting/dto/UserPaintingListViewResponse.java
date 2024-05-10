package com.pickkasso.domain.painting.dto;

import java.time.LocalDateTime;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.painting.domain.Painting;

import lombok.Getter;

@Getter
public class UserPaintingListViewResponse {
    private String paintingLink;
    private String paintingTitle;
    private LocalDateTime createdAt;
    private String curriculumTitle;
    private String curriculumInfo;

    public UserPaintingListViewResponse(Painting painting, Curriculum curriculum) {
        this.paintingLink = painting.getPaintingLink();
        this.paintingTitle = painting.getPaintingTitle();
        // this.createdAt = globalResponse.getCreatedAt();
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
    }
}
