package com.pingpong.quoteBakery.sys.domain;

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
 * 공통코드 Entity
 */
@Entity
@Table(name = "comm_cds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommCd extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "comm_cd_sequence", allocationSize = 50)
    @Column(name = "comm_cd_id")
    private Long commCdId;

    @Column(name = "commCd", length = 100) // 공통코드
    private String commCd;


    @Column(name = "commNm", length = 100) // 공통코드명
    private String commNm;
}