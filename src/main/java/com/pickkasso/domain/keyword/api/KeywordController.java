package com.pickkasso.domain.keyword.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.keyword.dto.request.AddKeywordRequest;
import com.pickkasso.domain.keyword.dto.request.AddTodaykeywordRequest;
import com.pickkasso.domain.keyword.dto.response.*;
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

    @GetMapping("/todayword")
    public TodaykeywordResponse findTodayKeyword() {

        return keywordService.findTodayKeyword();
    }

    @GetMapping("/todayword/all")
    public List<AllTodaykeywordResponse> findAllTodayKeyword() {
        return keywordService.findAllTodayKeyword();
    }

    @PostMapping("/todayword")
    public AddTodaykeywordResponse addTodayKeyword(@RequestBody AddTodaykeywordRequest request)
            throws ParseException {

        if (request.getDate().equals("null")) {
            return keywordService.addTodayKeyword(request.getKeyword());
        } else {
            return keywordService.addTodayKeyword(request.getDate(), request.getKeyword());
        }
    }

    @DeleteMapping("/todayword/{id}")
    public void deleteTodaykeyword(@PathVariable Long id) {
        keywordService.deleteTodaykeyword(id);
    }

    @DeleteMapping("/todayword/all")
    public void deleteAllTodaykeyword() {
        keywordService.deleteAllTodaykeyword();
    }
}
