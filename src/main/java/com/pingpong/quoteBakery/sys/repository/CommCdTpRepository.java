package com.pingpong.quoteBakery.sys.repository;

import com.pingpong.quoteBakery.sys.domain.CommCdTp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommCdTpRepository extends JpaRepository<CommCdTp, Long> {
    CommCdTp findByCommCdTpCd(String cd);
    List<CommCdTp> findAllByCommCdTpCdIn(List<String> tpCds);
}

