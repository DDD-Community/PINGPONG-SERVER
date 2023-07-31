package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import java.util.List;

public interface UserPrefService {
    /**
     * 사용자의 취향을 선택할 수 있는 콤보리스트 조회
     * @return
     */
    List<CommCdTpDto> searchUserPrefCode();

    /**
     * 사용자 취향 저장
     * @param userPrefDto
     * @return
     */
    Long saveUserPref(UserPrefDto userPrefDto);

    /**
     * @param userId
     * @return
     */
    UserPrefDto getUserPrefByUserId(Long userId);
}

