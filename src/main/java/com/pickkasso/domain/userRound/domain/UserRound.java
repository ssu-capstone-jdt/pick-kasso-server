package com.pickkasso.domain.userRound.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRound {
    @EmbeddedId private UserRoundId id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @MapsId("round_id")
    @JoinColumn(name = "round_id")
    private Round round;

    @Column(name = "progress_state")
    private boolean progressState;

    // true : 진행 중
    public UserRound(Member member, Round round) {
        this.member = member;
        this.round = round;
        this.progressState = false;
    }

    public void changeState() {
        if (progressState) {
            progressState = false;
        } else {
            progressState = true;
        }
    }
}
