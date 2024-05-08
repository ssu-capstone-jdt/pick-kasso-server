package com.pickkasso.domain.curriculum.dto;

import com.pickkasso.domain.usercurriculum.domain.StateType;

import lombok.Getter;

@Getter
public class UserCurriculumListViewResponse {
    private CurriculumResponse curriculumResponse;
    private StateType state;
}
