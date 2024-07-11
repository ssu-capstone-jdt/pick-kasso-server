package com.pickkasso.domain.keyword.dto.response;

import java.util.List;

import com.pickkasso.domain.keyword.domain.Keyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddKeywordResponse {
    private Long id;
    private String keywordTitle;
    private String imageLink;
    private List<String> tag;

    public AddKeywordResponse(Keyword keyword) {
        this.id = keyword.getId();
        this.keywordTitle = keyword.getKeywordTitle();
        this.imageLink = keyword.getImageLink();
        this.tag = keyword.getTag();
    }
}
