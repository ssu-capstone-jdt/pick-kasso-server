package com.pickkasso.global.error.exception;

import lombok.Getter;

@Getter
public class PaintingException extends RuntimeException {
    public PaintingException() {
        super("삭제 권한이 없습니다. ");
    }
}
