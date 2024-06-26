// AllPaintingListViewResponse
package com.pickkasso.domain.painting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AllPaintingListViewResponse {
    private Long curriculumId;
    private String memberNickname;
    private String paintingLink;
    private String paintingTitle;
    private String curriculumTitle;
    private String curriculumInfo;

    public AllPaintingListViewResponse(
            Long curriculumId,
            String memberNickname,
            String paintingLink,
            String paintingTitle,
            String curriculumTitle,
            String curriculumInfo) {
        this.curriculumId = curriculumId;
        this.memberNickname = memberNickname;
        this.paintingLink = paintingLink;
        this.paintingTitle = paintingTitle;
        this.curriculumTitle = curriculumTitle;
        this.curriculumInfo = curriculumInfo;
    }
}
