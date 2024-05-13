package com.pickkasso.domain.userRound.dto;

import lombok.Getter;

@Getter
public class UserRoundResponse {
    private boolean progressState;

    public void setUserRoundResponse(boolean progressState) {
        this.progressState = progressState;
    }
}
