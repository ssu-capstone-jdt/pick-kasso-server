package com.pickkasso.global.error.exception;

import lombok.Getter;

@Getter
public class CurriculumException extends RuntimeException {
    public CurriculumException(Long curriculumId) {
        super("This curriculum already downloaded: " + curriculumId);
    }
}
