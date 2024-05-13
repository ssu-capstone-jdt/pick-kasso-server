package com.pickkasso.domain.curriculum.dto;

import com.pickkasso.domain.usercurriculum.domain.StateType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllCurriculumListViewResponse {
    private CurriculumResponse curriculumResponse;
    private StateType state;
}
