package com.pickkasso.domain.painting.domain;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "painting_link", nullable = false)
    private String paintingLink;

    @Column(name = "painting_title")
    private String paintingTitle;

    @Column(name = "painting_state")
    private boolean paintingState;





    @Builder
    public Painting(String userId, String paintingLink, String paintingTitle, boolean paintingState){
        this.userId = userId;
        this.paintingLink = paintingLink;
        this.paintingTitle = paintingTitle;
        this.paintingState = paintingState;
    }


}
