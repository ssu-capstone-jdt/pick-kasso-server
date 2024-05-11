package com.pickkasso.domain.userRound.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoundId implements Serializable {
    private Long userId;
    private Long roundId;
}