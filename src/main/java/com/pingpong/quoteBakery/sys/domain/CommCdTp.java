package com.pingpong.quoteBakery.sys.domain;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.com.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * 공통코드 타입 Entity
 */
@Entity
@Table(name = "comm_cd_tps")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommCdTp extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "comm_cd_tp_cd_sequence", allocationSize = 50)
    @Column(name = "comm_cd_tp_id")
    private Long commCdTpId;

    @Column(name = "commCdTpCd", length = 100) // 공통코드유형코드
    private String commCdTpCd;


    @Column(name = "commCdTpNm", length = 100) // 공통코드유형명
    private String commCdTpNm;

    @OneToMany(mappedBy = "commCdId")
    private Set<CommCd> commCds = new HashSet<>();
}