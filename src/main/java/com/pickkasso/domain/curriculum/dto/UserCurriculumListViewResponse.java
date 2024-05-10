package com.pickkasso.domain.curriculum.dto;

import com.pickkasso.domain.usercurriculum.domain.StateType;

import lombok.Getter;

@Getter
public class UserCurriculumListViewResponse {
    private CurriculumResponse curriculumResponse;
    private StateType state;

    public UserCurriculumListViewResponse(CurriculumResponse curriculumResponse, StateType state){
        this.curriculumResponse = curriculumResponse;
        this.state = state;
    }
}
