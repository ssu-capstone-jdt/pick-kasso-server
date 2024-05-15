package com.pickkasso.domain.usercurriculum.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
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

    @Builder
    public UserCurriculum(Member member, Curriculum curriculum) {
        this.member = member;
        this.curriculum = curriculum;
        this.state = StateType.InProgress;
    }

    public static UserCurriculum createUserCurriculum(Member member, Curriculum curr) {
        return UserCurriculum.builder().member(member).curriculum(curr).build();
    }
}
