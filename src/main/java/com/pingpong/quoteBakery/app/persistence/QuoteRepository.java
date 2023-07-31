package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long>, QuoteRepositoryCustom {
}

