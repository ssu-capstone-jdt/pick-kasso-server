package com.pickkasso.domain.keyword.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodaykeywordRequest {
    private String date;
    private List<String> keyword;
}
