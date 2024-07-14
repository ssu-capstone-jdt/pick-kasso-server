package com.pickkasso.domain.chatGPT.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddPromptRequest {
    private String genre;
    private int year;
    private String paintGenre;
    private String mood;
}
