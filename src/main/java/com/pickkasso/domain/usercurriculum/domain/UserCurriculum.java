package com.pickkasso.domain.usercurriculum.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserCurriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userCurriculum_id")
    private Long userCurriculum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Enumerated(EnumType.STRING)
    private StateType state;

    public UserCurriculum(Member member, Curriculum curriculum) {
        this.member = member;
        this.curriculum = curriculum;
        this.state = StateType.InProgress;
    }
}
