package com.pickkasso.domain.keyword.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddKeywordRequest {
    private String keywordTitle;
    private List<String> tag;
}
