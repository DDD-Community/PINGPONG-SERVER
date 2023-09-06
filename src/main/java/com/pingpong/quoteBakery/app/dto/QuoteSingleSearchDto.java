package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/*
 * 맛, 출처, 상황에 대한 선택지를 단 건만 선택할 수 있는 경우 사용
 * */
public class QuoteSingleSearchDto extends BaseDto {
    private String flavor;

    private String source;

    private String mood;
}

