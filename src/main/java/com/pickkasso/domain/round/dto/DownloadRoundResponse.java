package com.pickkasso.domain.round.dto;

import lombok.Getter;

@Getter
public class DownloadRoundResponse {
    private int order;
    private int time;
    private String explanation;
    private Long Id;
    private UserRoundUploadStatus isUploadSuccessful;

    public DownloadRoundResponse(
            int order,
            int time,
            String explanation,
            Long Id,
            UserRoundUploadStatus isUploadSuccessful) {
        this.order = order;
        this.time = time;
        this.explanation = explanation;
        this.Id = Id;
        this.isUploadSuccessful = isUploadSuccessful;
    }
}
