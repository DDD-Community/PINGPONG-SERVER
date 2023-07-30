package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;

import java.util.List;

public interface CommCdTpService {
    /**
     * 유형코드이름으로 공통코드유형코드를 조회함.
     * @param commCdTpCd
     * @return
     */
    CommCdTpDto getCommCdTpByCd(String commCdTpCd);
}

