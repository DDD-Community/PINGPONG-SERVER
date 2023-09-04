package com.pingpong.quoteBakery.sys.domain;


import com.pingpong.quoteBakery.com.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 공통코드 Entity
 */
@Entity
@Table(name = "withdrawal_reasons")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WithdrawalReason extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "withdrawal_reasons_sequence", allocationSize = 50)
    @Column(name = "withdrawal_reason_id")
    private Long withdrawal_reason_id;

    @Column(name = "reason", length = 2000)
    private String reason;
}
