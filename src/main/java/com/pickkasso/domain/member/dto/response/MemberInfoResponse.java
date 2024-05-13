package com.pickkasso.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberInfoResponse {
    private final String nickname;
    private final String profile;
}
