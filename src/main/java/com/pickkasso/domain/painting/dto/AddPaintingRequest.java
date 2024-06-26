// AddPaintingRequest
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
    private String paintingTitle;
    private String paintingText;
    private boolean paintingState;
    private Long roundId;

    public Painting toEntity() {
        return Painting.builder()
                .paintingTitle(paintingTitle)
                .paintingText(paintingText)
                .paintingState(paintingState)
                .roundId(roundId)
                .build();
    }
}
