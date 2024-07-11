package com.pickkasso.domain.keyword.dto.response;

import java.util.List;

import com.pickkasso.domain.keyword.domain.TodayKeyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodaykeywordResponse {
    private List<String> keyword;

    public TodaykeywordResponse(TodayKeyword todayKeyword) {
        this.keyword = todayKeyword.getKeyword();
    }
}
