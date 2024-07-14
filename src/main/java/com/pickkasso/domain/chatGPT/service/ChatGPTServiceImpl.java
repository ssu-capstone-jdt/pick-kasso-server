// package com.pickkasso.domain.chatGPT.service;
//
// import java.util.List;
// import java.util.Map;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
//
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.pickkasso.domain.chatGPT.dto.ChatGPTRequest;
// import com.pickkasso.infra.config.chatGPT.ChatGPTConfig;
//
// import lombok.extern.slf4j.Slf4j;
//
///// **
//// * ChatGPT Service 구현체
//// *
//// * @author : lee
//// * @fileName : ChatGPTServiceImpl
//// * @since : 12/29/23
//// */
// @Slf4j
// @Service
// public class ChatGPTServiceImpl implements ChatGPTService {
//
//    private final ChatGPTConfig chatGPTConfig;
//
//    public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
//        this.chatGPTConfig = chatGPTConfig;
//    }
//
//    @Value("${openai.model}")
//    private String model;
//
//    /**
//     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
//     *
//     * @return
//     */
//    //    @Override
//    public List<Map<String, Object>> modelList() {
//        log.debug("Fetching model list...");
//        List<Map<String, Object>> resultList = null;
//
//        HttpHeaders headers = chatGPTConfig.httpHeaders();
//        ResponseEntity<String> response =
//                chatGPTConfig
//                        .restTemplate()
//                        .exchange(
//                                "https://api.openai.com/v1/models",
//                                HttpMethod.GET,
//                                new HttpEntity<>(headers),
//                                String.class);
//
//        try {
//            Map<String, Object> data =
//                    objectMapper.readValue(response.getBody(), new TypeReference<>() {});
//            resultList = (List<Map<String, Object>>) data.get("data");
//            resultList.forEach(
//                    object -> {
//                        log.debug("ID: {}", object.get("id"));
//                        log.debug("Object: {}", object.get("object"));
//                        log.debug("Created: {}", object.get("created"));
//                        log.debug("Owned By: {}", object.get("owned_by"));
//                    });
//        } catch (JsonMappingException e) {
//            log.error("JsonMappingException: {}", e.getMessage());
//        } catch (JsonProcessingException e) {
//            log.error("JsonProcessingException: {}", e.getMessage());
//        }
//        return resultList;
//    }
//
//    /**
//     * 모델이 유효한지 확인하는 비즈니스 로직
//     *
//     * @param modelName
//     * @return
//     */
//    @Override
//    public Map<String, Object> isValidModel(String modelName) {
//        log.debug("[+] 모델이 유효한지 조회합니다. 모델 : " + modelName);
//        Map<String, Object> result;
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = chatGPTConfig.httpHeaders();
//
//        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
//        ResponseEntity response =
//                chatGPTConfig
//                        .restTemplate()
//                        .exchange(
//                                "<https://api.openai.com/v1/models/>" + modelName,
//                                HttpMethod.GET,
//                                new HttpEntity<>(headers),
//                                String.class);
//        try {
//            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
//            ObjectMapper om = new ObjectMapper();
//            result = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
//
//    /**
//     * ChatGTP 프롬프트 검색
//     *
//     * @param completionRequestDto
//     * @return
//     */
//    @Override
//    public List<Map<String, Object>> prompt(ChatGPTRequest completionRequestDto) {
//        log.debug("[+] 프롬프트를 수행합니다.");
//
//        List<Map<String, Object>> result = null;
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = chatGPTConfig.httpHeaders();
//
//        String requestBody = "";
//        ObjectMapper om = new ObjectMapper();
//
//        // [STEP3] properties의 model을 가져와서 객체에 추가합니다.
//        completionRequestDto =
//                completionRequestDto
//                        .builder()
//                        .model(model)
//                        .prompt(completionRequestDto.getPrompt())
//                        .temperature(0.8f)
//                        .build();
//
//        try {
//            // [STEP4] Object -> String 직렬화를 구성합니다.
//            requestBody = om.writeValueAsString(completionRequestDto);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
//        HttpEntity requestEntity = new HttpEntity<>(completionRequestDto, headers);
//        ResponseEntity response =
//                chatGPTConfig
//                        .restTemplate()
//                        .exchange(
//                                "<https://api.openai.com/v1/completions>",
//                                HttpMethod.POST,
//                                requestEntity,
//                                String.class);
//        try {
//            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
//            result = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        return result;
//    }
// }

package com.pickkasso.domain.chatGPT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickkasso.domain.chatGPT.dto.ChatGPTRequest;
import com.pickkasso.infra.config.chatGPT.ChatGPTConfig;

import lombok.extern.slf4j.Slf4j;

/// **
// * ChatGPT Service 구현체
// *
// * @fileName : ChatGPTServiceImpl
// */
@Slf4j
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;
    private final ObjectMapper objectMapper;

    @Value("${openai.model}")
    private String model;

    public ChatGPTServiceImpl(ChatGPTConfig chatGPTConfig) {
        this.chatGPTConfig = chatGPTConfig;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<Map<String, Object>> modelList() {
        log.debug("Fetching model list...");
        List<Map<String, Object>> resultList = null;

        HttpHeaders headers = chatGPTConfig.httpHeaders();
        ResponseEntity<String> response =
                chatGPTConfig
                        .restTemplate()
                        .exchange(
                                "https://api.openai.com/v1/models",
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                String.class);

        try {
            Map<String, Object> data =
                    objectMapper.readValue(response.getBody(), new TypeReference<>() {});
            resultList = (List<Map<String, Object>>) data.get("data");
            resultList.forEach(
                    object -> {
                        log.debug("ID: {}", object.get("id"));
                        log.debug("Object: {}", object.get("object"));
                        log.debug("Created: {}", object.get("created"));
                        log.debug("Owned By: {}", object.get("owned_by"));
                    });
        } catch (JsonMappingException e) {
            log.error("JsonMappingException: {}", e.getMessage());
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException: {}", e.getMessage());
        }
        return resultList;
    }

    @Override
    public Map<String, Object> isValidModel(String modelName) {
        log.debug("Checking if model is valid: {}", modelName);
        Map<String, Object> result = null;

        HttpHeaders headers = chatGPTConfig.httpHeaders();
        ResponseEntity<String> response =
                chatGPTConfig
                        .restTemplate()
                        .exchange(
                                "https://api.openai.com/v1/models/" + modelName,
                                HttpMethod.GET,
                                new HttpEntity<>(headers),
                                String.class);
        try {
            result = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
        return result;
    }

    @Override
    public Map<String, Object> prompt(String prompt) {
        log.debug("Performing prompt...");

        Map<String, Object> result = null;

        HttpHeaders headers = chatGPTConfig.httpHeaders();

        ChatGPTRequest chatGPTRequest =
                ChatGPTRequest.builder()
                        .model(model)
                        .prompt(prompt)
                        .maxTokens(1000)
                        .temperature(0.8f)
                        .build();

        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(chatGPTRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize request body", e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response =
                chatGPTConfig
                        .restTemplate()
                        .exchange(
                                "https://api.openai.com/v1/completions",
                                HttpMethod.POST,
                                requestEntity,
                                String.class);

        try {
            result = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON response", e);
        }
        return result;
    }

    //    @Override
    //    public String prompt(String prompt) {
    //        log.debug("Performing prompt...");
    //
    //        HttpHeaders headers = new HttpHeaders();
    //
    //        ChatGPTRequest chatGPTRequest =
    //                ChatGPTRequest.builder()
    //                        .model(model)
    //                        .prompt(prompt)
    //                        .maxTokens(1000)
    //                        .temperature(0.8f)
    //                        .build();
    //
    //        String requestBody;
    //        try {
    //            requestBody = objectMapper.writeValueAsString(chatGPTRequest);
    //            log.debug("API Request Body: " + requestBody);
    //        } catch (JsonProcessingException e) {
    //            throw new RuntimeException("Failed to serialize request body", e);
    //        }
    //
    //        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    //
    //        ResponseEntity<String> response =
    //                chatGPTConfig
    //                        .restTemplate()
    //                        .exchange(
    //                                "https://api.openai.com/v1/completions",
    //                                HttpMethod.POST,
    //                                requestEntity,
    //                                String.class);
    //
    //        try {
    //            JsonNode jsonNode = objectMapper.readTree(response.getBody());
    //            return jsonNode.path("choices").get(0).path("text").asText();
    //        } catch (JsonProcessingException e) {
    //            throw new RuntimeException("Failed to parse JSON response", e);
    //        }
    //    }
}
