package com.pickkasso.domain.round.dto;

import lombok.Getter;

@Getter
public class RoundResponse {
    private int order;
    private String time;
    private String explanation;

    public RoundResponse(int order, String time, String explanation) {
        this.order = order;
        this.time = time;
        this.explanation = explanation;
    }
}
