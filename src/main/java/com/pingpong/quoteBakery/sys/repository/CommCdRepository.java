package com.pingpong.quoteBakery.sys.repository;

import com.pingpong.quoteBakery.sys.domain.CommCd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommCdRepository extends JpaRepository<CommCd, Long> {
}

