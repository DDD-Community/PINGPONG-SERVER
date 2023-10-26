package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LikeCreateResource {

    @Schema(description = "사용자ID")
    private Long userId;

    @Schema(description = "명언ID")
    private Long quoteId;
}


