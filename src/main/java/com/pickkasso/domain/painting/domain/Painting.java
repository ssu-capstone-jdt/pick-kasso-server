// Painting
package com.pickkasso.domain.painting.domain;

import jakarta.persistence.*;

import com.pickkasso.domain.common.model.BaseEntity;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Painting extends BaseEntity {
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

    @Column(name = "painting_text")
    private String paintingText;

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
            String paintingText,
            boolean paintingState) {
        this.memberId = memberId;
        this.roundId = roundId;
        this.paintingLink = paintingLink;
        this.paintingTitle = paintingTitle;
        this.paintingText = paintingText;
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
