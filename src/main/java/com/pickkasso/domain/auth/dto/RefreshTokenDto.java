package com.pickkasso.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RefreshTokenDto {
    private Long memberId;
    private String token;
    private Long expiredTime;
}
