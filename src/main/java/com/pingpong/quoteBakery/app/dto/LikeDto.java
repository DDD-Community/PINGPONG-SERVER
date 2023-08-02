package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LikeDto extends BaseDto {
    private Long likeId;
    private Long userId;
    private Long quoteId;
}


