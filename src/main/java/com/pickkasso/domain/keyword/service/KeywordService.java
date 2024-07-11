package com.pickkasso.domain.keyword.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.keyword.dao.FixedDateRepository;
import com.pickkasso.domain.keyword.dao.KeywordRepository;
import com.pickkasso.domain.keyword.dao.TodaykeywordRepository;
import com.pickkasso.domain.keyword.domain.FixedDate;
import com.pickkasso.domain.keyword.domain.Keyword;
import com.pickkasso.domain.keyword.domain.TodayKeyword;
import com.pickkasso.domain.keyword.dto.request.AddKeywordRequest;
import com.pickkasso.domain.keyword.dto.response.*;
import com.pickkasso.domain.painting.service.PaintingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {

    private final KeywordRepository keywordRepository;
    private final TodaykeywordRepository todaykeywordRepository;

    private final FixedDateRepository fixedDateRepository;
    private final PaintingService paintingService;

    public List<KeywordResponse> findAllKeyword() {
        List<KeywordResponse> keywordResponseList = new ArrayList<>();
        List<Keyword> keywordList = keywordRepository.findAll();
        for (Keyword keyword : keywordList) {
            KeywordResponse keywordResponse = new KeywordResponse(keyword);
            keywordResponseList.add(keywordResponse);
        }

        return keywordResponseList;
    }

    public AddKeywordResponse addKeyword(MultipartFile file, AddKeywordRequest addKeywordRequest)
            throws IOException {
        String fileUrl = paintingService.uploadPainting(file);
        Keyword keyword =
                Keyword.createKeyword(
                        addKeywordRequest.getKeywordTitle(),
                        fileUrl,
                        addKeywordRequest.getTag(),
                        0L);

        keywordRepository.save(keyword);
        return new AddKeywordResponse(keyword);
    }

    public AddUsedCountResponse addUsedCount(Long id) {
        Keyword keyword = keywordRepository.findById(id).orElseThrow();
        return new AddUsedCountResponse(id, keyword.addUsedCount());
    }

    public AddTodaykeywordResponse addTodayKeyword(List<String> keyword) throws ParseException {
        TodayKeyword todayKeyword = TodayKeyword.createTodaykeyword("null", keyword);
        todayKeyword.addDate();
        todaykeywordRepository.save(todayKeyword);

        return new AddTodaykeywordResponse(todayKeyword);
    }

    public AddTodaykeywordResponse addTodayKeyword(String date, List<String> keyword)
            throws ParseException {
        boolean isExitsDate = todaykeywordRepository.existsByDate(date);
        TodayKeyword todayKeyword;
        if (isExitsDate) {
            todayKeyword = todaykeywordRepository.findByDate(date);
            List<String> keywordList = todayKeyword.getKeyword();
            todaykeywordRepository.deleteById(todayKeyword.getId());
            todayKeyword = TodayKeyword.createTodaykeyword(date, keyword);

            addTodayKeyword(keywordList);
            todaykeywordRepository.save(todayKeyword);

        } else {
            todayKeyword = TodayKeyword.createTodaykeyword(date, keyword);
            todaykeywordRepository.save(todayKeyword);
        }

        return new AddTodaykeywordResponse(todayKeyword);
    }

    public TodaykeywordResponse findTodayKeyword() {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String date = simpleDateFormat.format(nowDate);
        TodayKeyword todayKeyword = todaykeywordRepository.findByDate(date);
        return new TodaykeywordResponse(todayKeyword);
    }

    public List<AllTodaykeywordResponse> findAllTodayKeyword() {
        List<TodayKeyword> todayKeywordList = todaykeywordRepository.findAll();

        return todayKeywordList.stream()
                .map(AllTodaykeywordResponse::new)
                .collect(Collectors.toList());
    }

    public void deleteTodaykeyword(Long id) {
        todaykeywordRepository.deleteById(id);
    }

    public void deleteAllTodaykeyword() {
        todaykeywordRepository.deleteAll();
    }

}
