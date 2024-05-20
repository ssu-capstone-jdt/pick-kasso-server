package com.pickkasso.global.error.exception;

import lombok.Getter;

@Getter
public class RoundNotFoundException extends RuntimeException {
    public RoundNotFoundException(Long roundId) {
        super("Round not found with id: " + roundId);
    }
}
