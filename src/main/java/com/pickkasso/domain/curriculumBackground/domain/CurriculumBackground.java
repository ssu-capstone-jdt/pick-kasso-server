package com.pickkasso.domain.curriculumBackground.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurriculumBackground {
    @OneToOne
    @MapsId("curriculum_id")
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Column(name = "background_link", length = 10)
    private String backgroundLink;

    @Builder
    public CurriculumBackground(Curriculum curriculum, String backgrooundLink) {
        this.curriculum = curriculum;
        this.backgroundLink = backgrooundLink;
    }
}
