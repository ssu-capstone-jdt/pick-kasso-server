package com.pickkasso.domain.round.dto;

import lombok.Getter;

@Getter
public class RoundResponse {
    private int order;
    private int time;
    private String explanation;

    public RoundResponse(int order, int time, String explanation) {
        this.order = order;
        this.time = time;
        this.explanation = explanation;
    }
}
