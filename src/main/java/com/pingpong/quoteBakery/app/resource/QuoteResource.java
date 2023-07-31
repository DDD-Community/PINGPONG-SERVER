package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
public class QuoteResource {
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


    @Schema(description = "좋아요")
    private List<LikeDto> likes = new ArrayList<>();


    @Schema(description = "보관")
    private List<ScrapDto> scraps = new ArrayList<>();
}

