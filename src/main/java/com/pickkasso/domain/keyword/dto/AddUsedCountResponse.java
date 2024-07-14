package com.pickkasso.domain.keyword.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUsedCountResponse {
    private Long id;
    private Long usedCount;

    public AddUsedCountResponse(Long id, Long usedCount) {
        this.id = id;
        this.usedCount = usedCount;
    }
}
