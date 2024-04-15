package com.pickkasso.domain.curriculum.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "curriculum_title", length = 10)
    private String curriculumTitle;

    @Column(name = "curriculum_info", length = 20)
    private String curriculumInfo;


    @Column(name = "curriculum_explanation", length = 100)
    private String curriculumExplanation;

    @Column(name = "curriculum_round_count")
    private int curriculumRoundCount;

    @Column(name = "curriculum_difficulty", length = 10)
    private String curriculumDifficulty;
}
