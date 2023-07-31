package com.pingpong.quoteBakery.app.dto;

import lombok.Data;


@Data
public class LikeDto {
    private Long likeId;
    private Long userId;
    private Long quoteId;
}


