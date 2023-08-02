package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPrefDto extends BaseDto {
    private Long userPrefId;

    private String flavor;

    private String source;

    private Long userId;
}

