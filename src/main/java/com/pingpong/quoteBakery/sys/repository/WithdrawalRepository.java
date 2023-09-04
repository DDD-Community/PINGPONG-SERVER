package com.pingpong.quoteBakery.sys.repository;

import com.pingpong.quoteBakery.sys.domain.WithdrawalReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<WithdrawalReason, Long> {
}
