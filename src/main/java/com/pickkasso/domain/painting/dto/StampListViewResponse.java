package com.pickkasso.domain.painting.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class StampListViewResponse {
    LocalDateTime localDateTime;

    public StampListViewResponse(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
