package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
/*
* 맛, 출처, 상황에 대한 선택지를 멀티플로 선택할 수 있는 경우 사용
* */
public class QuoteMultiSearchDto extends BaseDto {
    // search - filter
    private String keyword;
    // search - bake
    private String orderBy;

    // search - common
    private List<String> flavors;
    private List<String> sources;
    private List<String> moods;
}

