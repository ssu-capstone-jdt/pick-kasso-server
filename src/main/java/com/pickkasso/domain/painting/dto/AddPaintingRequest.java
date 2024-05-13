package com.pickkasso.domain.painting.dto;

import com.pickkasso.domain.painting.domain.Painting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddPaintingRequest {
    private String paintingLink;
    private String paintingTitle;
    private boolean paintingState;

    public Painting toEntity() {
        return Painting.builder()
                .paintingLink(paintingLink)
                .paintingTitle(paintingTitle)
                .paintingState(paintingState)
                .build();
    }
    // 사진 업로드
}
