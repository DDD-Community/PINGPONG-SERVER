package com.pingpong.quoteBakery.sys.domain;

import com.pingpong.quoteBakery.com.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

/**
 * 공통코드 Entity
 */
@Entity
@Table(name = "comm_cds")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Column(name = "sortSeq") // 정렬순서
    private Long sortSeq;

    @Column(name = "useYn") // 사용여부
    private Boolean useYn;

    @ManyToOne
    @JoinColumn(name = "comm_cd_tp_id")
    private CommCdTp commCdTp;
}