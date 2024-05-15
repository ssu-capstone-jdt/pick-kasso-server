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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "background_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Column(name = "background_link", length = 10)
    private String backgroundLink;

    @Builder
    public CurriculumBackground(Curriculum curriculum, String backgrooundLink) {
        this.curriculum = curriculum;
        this.backgroundLink = backgrooundLink;
    }

    public static CurriculumBackground createCurrBackground(
            Curriculum curr, String currBackground) {
        return CurriculumBackground.builder()
                .curriculum(curr)
                .backgrooundLink(currBackground)
                .build();
    }
}
