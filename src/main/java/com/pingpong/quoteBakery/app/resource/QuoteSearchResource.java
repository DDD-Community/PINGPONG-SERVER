package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuoteSearchResource {
    @Schema(description = "키워드")
    private String keyword;

    @Schema(description = "맛")
    private List<String> flavors;


    @Schema(description = "출처")
    private List<String> sources;


    @Schema(description = "상황")
    private List<String> moods;

    @Schema(description = "정렬방식 ex)ASC/DESC")
    private String orderBy;
}

