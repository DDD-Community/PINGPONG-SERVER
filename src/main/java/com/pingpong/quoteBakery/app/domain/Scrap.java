package com.pingpong.quoteBakery.app.domain;

import com.pingpong.quoteBakery.com.entity.BaseEntity;
import com.pingpong.quoteBakery.sys.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * 명언 좋아요 Entity
 */
@Entity
@Table(name = "scraps")
public class Scrap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "my_sequence", allocationSize = 50)
    private Long scrap_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
}
