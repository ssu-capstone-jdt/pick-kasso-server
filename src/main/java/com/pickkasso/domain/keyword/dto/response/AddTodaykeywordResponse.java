package com.pickkasso.domain.keyword.dto.response;

import java.util.List;

import com.pickkasso.domain.keyword.domain.TodayKeyword;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodaykeywordResponse {
    private Long id;
    private String date;
    private List<String> keyword;

    public AddTodaykeywordResponse(TodayKeyword todayKeyword) {
        this.id = todayKeyword.getId();
        this.date = todayKeyword.getDate();
        this.keyword = todayKeyword.getKeyword();
    }
}
