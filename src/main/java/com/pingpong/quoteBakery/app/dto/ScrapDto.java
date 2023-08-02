package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ScrapDto extends BaseDto {
    private Long scrapId;
    private Long userId;
    private Long quoteId;
}

