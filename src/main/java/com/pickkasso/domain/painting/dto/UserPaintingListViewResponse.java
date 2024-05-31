// UserPaintingListViewResponse
package com.pickkasso.domain.painting.dto;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.painting.domain.Painting;

import lombok.Getter;

@Getter
public class UserPaintingListViewResponse {
    private Long id;
    private String paintingLink;
    private String paintingTitle;
    private String curriculumTitle;
    private String curriculumInfo;

    public UserPaintingListViewResponse(Painting painting, Curriculum curriculum) {
        this.id = painting.getId();
        this.paintingLink = painting.getPaintingLink();
        this.paintingTitle = painting.getPaintingTitle();
        this.curriculumTitle = curriculum.getCurriculumTitle();
        this.curriculumInfo = curriculum.getCurriculumInfo();
    }
}
