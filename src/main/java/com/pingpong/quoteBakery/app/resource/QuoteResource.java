package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.com.resource.BaseResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuoteResource extends BaseResource {
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

    @Schema(description = "좋아요ID")
    private Long likeId;
}

