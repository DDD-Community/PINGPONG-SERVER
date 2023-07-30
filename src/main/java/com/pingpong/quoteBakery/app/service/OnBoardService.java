package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;

import java.util.List;

public interface OnBoardService {
    /**
     * 사용자의 취향을 선택할 수 있는 콤보리스트 조회
     * @return
     */
    List<CommCdTpDto> searchUserPrefCode();
}

