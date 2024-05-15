// Painting
package com.pickkasso.domain.painting.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Painting {
    //    @Id
    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //    @Column(name = "id", updatable = false)
    //    private Long id;
    //
    //    @Column(name = "user_id", nullable = false)
    //    private String userId;
    //
    //    @Column(name = "painting_link", nullable = false)
    //    private String paintingLink;
    //
    //    @Column(name = "painting_title")
    //    private String paintingTitle;
    //
    //    @Column(name = "painting_state")
    //    private boolean paintingState;
    //
    //    @Builder
    //    public Painting(
    //            String userId, String paintingLink, String paintingTitle, boolean paintingState) {
    //        this.userId = userId;
    //        this.paintingLink = paintingLink;
    //        this.paintingTitle = paintingTitle;
    //        this.paintingState = paintingState;
    //    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long memberId;

    @Column(name = "round_id", nullable = false)
    private Long roundId;

    @Column(name = "painting_link", nullable = false)
    private String paintingLink;

    @Column(name = "painting_title")
    private String paintingTitle;

    @Column(name = "painting_state")
    private boolean paintingState;

    @Column(name = "painting_Name")
    private String paintingName;

    @Builder
    public Painting(
            Long memberId,
            Long roundId,
            String paintingLink,
            String paintingTitle,
            boolean paintingState) {
        this.memberId = memberId;
        this.roundId = roundId;
        this.paintingLink = paintingLink;
        this.paintingTitle = paintingTitle;
        this.paintingState = paintingState;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setPaintingLink(String paintingLink) {
        this.paintingLink = paintingLink;
    }

    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }
}
