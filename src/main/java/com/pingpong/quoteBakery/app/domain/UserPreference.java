package com.pingpong.quoteBakery.app.domain;

import com.pingpong.quoteBakery.com.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자별 명언 취향 Entity
 */
@Entity
@Table(name = "user_preferences")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreference extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "user_pref_sequence", allocationSize = 50)
    @Column(name = "user_pref_id")
    private Long userPrefId;

    @Column(name = "flavor", length = 100) // 명언 맛
    private String flavor;


    @Column(name = "source", length = 100) // 명언 출처
    private String source;
}