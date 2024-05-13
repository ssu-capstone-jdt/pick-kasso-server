package com.pickkasso.domain.userRound.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoundId implements Serializable {
    private Long userId;
    private Long roundId;
}
