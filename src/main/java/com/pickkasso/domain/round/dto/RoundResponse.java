package com.pickkasso.domain.round.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class RoundResponse {
    private int order;
    private char time;
    private String explanation;

    public RoundResponse(int order, char time, String explanation){
        this.order = order;
        this.time = time;
        this.explanation = explanation;
    }
}
