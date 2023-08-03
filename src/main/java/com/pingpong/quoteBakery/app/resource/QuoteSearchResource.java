package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.com.resource.PageResource;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuoteSearchResource extends PageResource {
    @Schema(description = "키워드")
    private String keyword;

    @Schema(description = "맛 리스트")
    private List<String> flavors;


    @Schema(description = "출처 리스트")
    private List<String> sources;


    @Schema(description = "상황 리스트")
    private List<String> moods;

    @Schema(description = "정렬방식 ex)ASC/DESC")
    private String orderBy;
}

