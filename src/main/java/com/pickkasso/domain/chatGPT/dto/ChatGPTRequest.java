package com.pickkasso.domain.chatGPT.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// public class ChatGPTRequest {
//
//    private String model;
//
//    private String prompt;
//
//    private float temperature;
//
//    @Builder
//    ChatGPTRequest(String model, String prompt, float temperature) {
//        this.model = model;
//        this.prompt = prompt;
//        this.temperature = temperature;
//    }
// }
@Getter
@Builder
public class ChatGPTRequest {
    private String model;
    private String prompt;

    @JsonProperty("max_tokens")
    private Integer maxTokens;

    private Float temperature;
}
