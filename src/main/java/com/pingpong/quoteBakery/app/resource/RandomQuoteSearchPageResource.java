package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.com.resource.PageResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RandomQuoteSearchPageResource extends PageResource {
    @Schema(description = "사용자ID")
    private Long userId;

}

