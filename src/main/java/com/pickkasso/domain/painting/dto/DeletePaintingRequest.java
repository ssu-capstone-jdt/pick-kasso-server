package com.pickkasso.domain.painting.dto;

import com.pickkasso.domain.painting.domain.Painting;
import lombok.Getter;

@Getter
public class DeletePaintingRequest {
    private final Long id;

    public DeletePaintingRequest(Painting painting){
        this.id = painting.getId();

    }

}