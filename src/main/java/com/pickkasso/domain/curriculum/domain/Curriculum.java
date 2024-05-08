package com.pickkasso.domain.curriculum.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curriculum_id")
    private Long id;

    @Column(name = "curriculum_title", length = 10)
    private String curriculumTitle;

    @Column(name = "curriculum_info", length = 20)
    private String curriculumInfo;

    @Column(name = "curriculum_explanation", length = 100)
    private String curriculumExplanation;

    @Column(name = "curriculum_background")
    private String curriculumBackground;

    @Column(name = "curriculum_round_count")
    private int curriculumRoundCount;

    @Column(name = "curriculum_difficulty", length = 10)
    private String curriculumDifficulty;

    @OneToMany(mappedBy = "curriculm")
    private List<UserCurriculum> userCurriculums = new ArrayList<>();

    @Builder
    public Curriculum(
            String curriculumTitle,
            String curriculumInfo,
            String curriculumExplanation,
            String curriculumBackground,
            int curriculumRoundCount,
            String curriculumDifficulty) {
        this.curriculumTitle = curriculumTitle;
        this.curriculumInfo = curriculumInfo;
        this.curriculumExplanation = curriculumExplanation;
        this.curriculumBackground = curriculumBackground;
        this.curriculumRoundCount = curriculumRoundCount;
        this.curriculumDifficulty = curriculumDifficulty;
    }
}
