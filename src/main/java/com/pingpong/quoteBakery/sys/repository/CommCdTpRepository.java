package com.pingpong.quoteBakery.sys.repository;

import com.pingpong.quoteBakery.sys.domain.CommCd;
import com.pingpong.quoteBakery.sys.domain.CommCdTp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommCdTpRepository extends JpaRepository<CommCdTp, Long> {
    CommCdTp findByCommCdTpCd(String cd);
}

