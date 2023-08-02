package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.com.resource.BaseResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ScrapResource extends BaseResource {
    @Schema(description = "사용자ID")
    private Long userId;

    @Schema(description = "명언ID")
    private Long quoteId;
}


