package com.pickkasso.domain.userRound.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(name = "progress_state")
    private boolean progressState;

    @Builder
    public UserRound(Member member, Round round) {
        this.member = member;
        this.round = round;
        this.progressState = false;
    }

    public static UserRound createUserRound(Member member, Round round) {
        return UserRound.builder().member(member).round(round).build();
    }

    public void changeStateTrue() {
        progressState = true;
    }

    public void changeStateFalse() {
        progressState = true;
    }
}
