package com.pickkasso.domain.keyword.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.keyword.dto.AddKeywordRequest;
import com.pickkasso.domain.keyword.dto.AddKeywordResponse;
import com.pickkasso.domain.keyword.dto.AddUsedCountResponse;
import com.pickkasso.domain.keyword.dto.KeywordResponse;
import com.pickkasso.domain.keyword.service.KeywordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/keyword")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/all")
    public List<KeywordResponse> findKeyword() {

        return keywordService.findAllKeyword();
    }

    @PostMapping
    public AddKeywordResponse addKeyword(
            @RequestParam("file") MultipartFile file,
            @RequestPart("request") AddKeywordRequest request)
            throws IOException {

        return keywordService.addKeyword(file, request);
    }

    @PostMapping("/{id}")
    public AddUsedCountResponse addUsedCount(@PathVariable Long id) {
        return keywordService.addUsedCount(id);
    }
}
