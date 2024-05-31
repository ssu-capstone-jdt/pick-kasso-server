package com.pickkasso.domain.usercurriculum.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public void changeStateType() {
        this.state = StateType.Completed;
    }
}
