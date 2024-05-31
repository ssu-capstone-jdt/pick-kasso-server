package com.pickkasso.domain.userRound.dto;

import lombok.Getter;

@Getter
public class UserRoundCompleteResponse {
    boolean isUploadSuccessful;

    public UserRoundCompleteResponse(boolean isUploadSuccessful) {
        this.isUploadSuccessful = isUploadSuccessful;
    }
}
