package com.pickkasso.domain.keyword.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.keyword.dao.KeywordRepository;
import com.pickkasso.domain.keyword.domain.Keyword;
import com.pickkasso.domain.keyword.dto.AddKeywordRequest;
import com.pickkasso.domain.keyword.dto.AddKeywordResponse;
import com.pickkasso.domain.keyword.dto.AddUsedCountResponse;
import com.pickkasso.domain.keyword.dto.KeywordResponse;
import com.pickkasso.domain.painting.service.PaintingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {

    private final KeywordRepository keywordRepository;
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
}
