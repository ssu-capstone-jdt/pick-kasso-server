package com.pickkasso.domain.round.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.userRound.domain.UserRound;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Column(name = "round_seq")
    private int roundSeq;

    @Column(name = "time")
    private int time;

    @Column(name = "explanation", length = 50)
    private String explanation;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<UserRound> userRounds = new ArrayList<>();

    @Builder
    public Round(Curriculum curriculum, int order, int time, String explanation) {
        this.curriculum = curriculum;
        this.roundSeq = order;
        this.time = time;
        this.explanation = explanation;
    }
}
