package com.pickkasso.domain.curriculum.dto.request;

import java.util.List;

import com.pickkasso.domain.curriculum.domain.CurriculumDifficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCurriculumRequest {
    private String currTitle;
    private String currInfo;
    private String currExplanation;
    private int currRoundCount;
    private CurriculumDifficulty currDifficulty;
    private String currBackground;
    private List<Integer> times;
    private List<String> explanations;
}
