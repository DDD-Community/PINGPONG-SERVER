package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RandomQuoteSearchResource {
    @Schema(description = "사용자ID")
    private Long userId;

    @Schema(description = "맛")
    private String flavor;


    @Schema(description = "출처")
    private String source;


    @Schema(description = "상황")
    private String mood;
}

