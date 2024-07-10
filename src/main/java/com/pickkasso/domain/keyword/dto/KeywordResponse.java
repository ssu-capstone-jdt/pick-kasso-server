package com.pickkasso.domain.keyword.dto;

import java.util.List;

import com.pickkasso.domain.keyword.domain.Keyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordResponse {
    private Long id;
    private String keywordTitle;
    private String imageLink;
    private List<String> tag;
    private Long usedCount;

    public KeywordResponse(Keyword keyword) {
        this.id = keyword.getId();
        this.keywordTitle = keyword.getKeywordTitle();
        this.imageLink = keyword.getImageLink();
        this.tag = keyword.getTag();
        this.usedCount = keyword.getUsedCount();
    }
}
