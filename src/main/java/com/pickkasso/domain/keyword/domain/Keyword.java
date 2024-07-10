package com.pickkasso.domain.keyword.domain;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "keyword_title", length = 20)
    private String keywordTitle;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @Column(name = "tag")
    private List<String> tag;

    @Column(name = "used_count")
    private Long usedCount;

    @Builder
    public Keyword(String keywordTitle, String imageLink, List<String> tag, Long usedCount) {
        this.keywordTitle = keywordTitle;
        this.imageLink = imageLink;
        this.tag = tag;
        this.usedCount = usedCount;
    }

    public static Keyword createKeyword(
            String keywordTitle, String imageLink, List<String> tag, Long usedCount) {
        return Keyword.builder()
                .keywordTitle(keywordTitle)
                .imageLink(imageLink)
                .tag(tag)
                .usedCount(usedCount)
                .build();
    }

    public Long addUsedCount() {
        usedCount += 1;
        return usedCount;
    }
}
