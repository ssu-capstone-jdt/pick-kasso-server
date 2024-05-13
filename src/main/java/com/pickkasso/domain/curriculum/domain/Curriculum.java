package com.pickkasso.domain.curriculum.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculumBackground.domain.CurriculumBackground;
import com.pickkasso.domain.round.domain.Round;
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

    @Column(name = "curriculum_round_count")
    private int curriculumRoundCount;

    @Column(name = "curriculum_difficulty", length = 10)
    private String curriculumDifficulty;

    @OneToMany private List<UserCurriculum> userCurriculums = new ArrayList<>();

    @OneToMany private List<Round> rounds = new ArrayList<>();

    @OneToOne private CurriculumBackground curriculumBackgrounds;

    @Builder
    public Curriculum(
            String curriculumTitle,
            String curriculumInfo,
            String curriculumExplanation,
            int curriculumRoundCount,
            String curriculumDifficulty) {
        this.curriculumTitle = curriculumTitle;
        this.curriculumInfo = curriculumInfo;
        this.curriculumExplanation = curriculumExplanation;
        this.curriculumRoundCount = curriculumRoundCount;
        this.curriculumDifficulty = curriculumDifficulty;
    }

    public void setBackground(CurriculumBackground curriculumBackgrounds) {
        this.curriculumBackgrounds = curriculumBackgrounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds.stream().collect(Collectors.toList());
    }
}
