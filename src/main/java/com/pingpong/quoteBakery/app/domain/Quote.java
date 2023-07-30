package com.pingpong.quoteBakery.app.domain;

import com.pingpong.quoteBakery.com.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 명언 Entity
 */
@Entity
@Table(name = "quotes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quote extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "quote_sequence", allocationSize = 50)
    @Column(name = "quote_id")
    private Long quoteId;

    @Column(name = "content", length = 200) // 명언 내용
    private String content;

    @Column(name = "author", length = 100) // 명언 작성자
    private String author;


    @Column(name = "flavor", length = 50) // 명언 맛
    private String flavor;


    @Column(name = "source", length = 50) // 명언 출처
    private String source;

    @Column(name = "mood", length = 50) // 명언 상황(분위기)
    private String mood;

    @OneToMany(mappedBy = "quote")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "quote")
    private List<Scrap> scraps = new ArrayList<>();
}