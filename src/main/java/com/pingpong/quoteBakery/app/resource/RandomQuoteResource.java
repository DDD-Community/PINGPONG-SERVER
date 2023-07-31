package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class RandomQuoteResource {
    @Schema(description = "명언ID")
    private Long quoteId;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "말한사람/콘텐츠명")
    private String author;

    @Schema(description = "맛")
    private String flavor;


    @Schema(description = "출처")
    private String source;


    @Schema(description = "상황")
    private String mood;


    @Schema(description = "좋아요여부")
    private Boolean likeYn;


    @Schema(description = "보관여부")
    private Boolean scrapYn;
}

