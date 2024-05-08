package com.pickkasso.domain.usercurriculum.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserCurriculumId implements Serializable {
    private Long userId;
    private Long curriculumId;
}
