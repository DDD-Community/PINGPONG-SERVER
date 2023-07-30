package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;

import java.util.List;

public interface CommCdTpService {
    /**
     * 유형코드로 공통코드유형코드 단 건을 조회함.
     * @param commCdTpCd
     * @return
     */
    CommCdTpDto getCommCdTpByCd(String commCdTpCd);


    /**
     * 유형코드이름으로 공통코드유형코드 리스트를 조회함.
     * @param commCdTpCds
     * @return
     */
    List<CommCdTpDto> getCommCdTpListByCd(List<String> commCdTpCds);

    /**
     * 유형코드id로 공통코드유형코드를 조회함.
     * @param commCdTpId
     * @return
     */
    CommCdTpDto getCommCdTpById(Long commCdTpId);


}

