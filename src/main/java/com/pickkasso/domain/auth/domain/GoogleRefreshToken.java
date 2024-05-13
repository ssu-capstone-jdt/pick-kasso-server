package com.pickkasso.domain.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GoogleRefreshToken {
    @Id private Long memberId;

    private String token;

    @Builder
    public GoogleRefreshToken(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
}
