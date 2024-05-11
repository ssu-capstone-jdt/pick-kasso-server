package com.pickkasso.domain.round.domain;

import com.pickkasso.domain.userRound.domain.UserRound;
import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id")
    private Long id;
    @ManyToOne
    @MapsId("curriculum_id")
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Column(name = "order")
    private int order;

    @Column(name = "time", length = 8)
    private char time;

    @Column(name = "explanation", length = 15)
    private String explanation;

    @OneToMany(mappedBy = "round")
    private List<UserRound> userRounds = new ArrayList<>();

    @Builder
    public Round(Curriculum curriculum, int order, char time, String explanation){
        this.curriculum = curriculum;
        this.order = order;
        this.time = time;
        this.explanation = explanation;
    }
}
