package com.pickkasso.domain.round.dto;

import lombok.Getter;

@Getter
public class RoundResponse {
    private int order;
    private int time;
    private String explanation;
    private Long Id;

    //    public RoundResponse(int order, int time, String explanation) {
    //        this.order = order;
    //        this.time = time;
    //        this.explanation = explanation;
    //    }

    public RoundResponse(int order, int time, String explanation, Long id) {
        this.order = order;
        this.time = time;
        this.explanation = explanation;
        this.Id = id;
    }
}
