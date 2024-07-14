package com.pickkasso.domain.chatGPT.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.pickkasso.domain.chatGPT.dto.AddPromptRequest;
import com.pickkasso.domain.chatGPT.service.ChatGPTService;


@RestController
@RequestMapping(value = "/api/v1/chatGpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    /** [API] ChatGPT 모델 리스트를 조회합니다. */
    @GetMapping("/modelList")
    public List<Map<String, Object>> selectModelList() {
        List<Map<String, Object>> result = chatGPTService.modelList();
        return result;
    }

    @GetMapping("/model")
    public Map<String, Object> isValidModel(@RequestParam(name = "modelName") String modelName) {
        Map<String, Object> result = chatGPTService.isValidModel(modelName);
        return result;
    }

    /** [API] ChatGPT 모델 리스트를 조회합니다. */
    //    @PostMapping("/prompt")
    //    public Map<String, Object> selectPrompt(@RequestBody AddPromptRequest addPromptRequest) {
    //        String prompt =
    //                "최근 "
    //                        + addPromptRequest.getYear()
    //                        + "년간 유행했던 "
    //                        + addPromptRequest.getGenre()
    //                        + "의 노래 하나 제목만 추천해줘. 다른 정보는 주지마.";
    //
    //        return chatGPTService.prompt(prompt);
    //    }
    //
    //        @PostMapping("/prompt")
    //        public List<Map<String, Object>> selectPrompt(
    //                @RequestBody ChatGPTRequest completionRequestDto) {
    //            return chatGPTService.prompt(completionRequestDto);
    //        }
    //    @PostMapping("/prompt")
    //    public Map<String, Object> selectPrompt(@RequestBody AddPromptRequest addPromptRequest) {
    //        String prompt =
    //                //                "최근 "
    //                //                        + addPromptRequest.getYear()
    //                //                        + "년간 유행했던 "
    //                //                        + addPromptRequest.getGenre()
    //                //                        + "의 노래를 딱 하나만 추천해주는데, 제목과 유튜브 링크 딱 두개의 정보를  줘. 다른
    // 말은 하지말고
    //                // 저 정보만 줘.";
    //                " 최근 "
    //                        + addPromptRequest.getYear()
    //                        + "년간 유행했던 "
    //                        + addPromptRequest.getGenre()
    //                        + "의 노래 중에서"
    //                        + addPromptRequest.getPaintGenre()
    //                        + " 그림 그릴때 듣기 좋은 노래를 하나만 추천해줘, 제목과 유튜브 링크 딱 두개의 정보를  줘. 다른 말은 하지말고 저
    // 정보만 줘.";
    //
    //        return chatGPTService.prompt(prompt);
    //    }

    @PostMapping("/prompt")
    public Map<String, Object> selectPrompt(@RequestBody AddPromptRequest addPromptRequest) {
        String prompt =
                //                "최근 "
                //                        + addPromptRequest.getYear()
                //                        + "년간 유행했던 "
                //                        + addPromptRequest.getGenre()
                //                        + "의 노래를 딱 하나만 추천해주는데, 제목과 유튜브 링크 딱 두개의 정보를  줘. 다른 말은 하지말고
                // 저 정보만 줘.";
                " 최근 "
                        + addPromptRequest.getYear()
                        + "년간 유행했던 "
                        + addPromptRequest.getGenre()
                        + "의 노래 중에서"
                        + addPromptRequest.getMood()
                        + " 느낌의 노래를 추천해줘, 제목과 유튜브 링크 딱 두개의 정보를  줘. 다른 말은 하지말고 저 정보만 줘.";

        return chatGPTService.prompt(prompt);
    }

    //    @PostMapping("/prompt")
    //    public String selectPrompt(@RequestBody AddPromptRequest addPromptRequest) {
    //        String prompt =
    //                "최근 "
    //                        + addPromptRequest.getYear()
    //                        + "년간 유행했던 "
    //                        + addPromptRequest.getGenre()
    //                        + "의 노래를 딱 하나만 추천해주는데, 제목과 유튜브 링크 딱 두개의 정보를 정보를 jason 형태로 줘. 다른 말은
    // 하지말고 저 정보만 줘.";
    //
    //        return chatGPTService.prompt(prompt);
    //    }
}
