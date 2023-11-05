package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserPrefUpdateResource {
    @Hidden
    @Schema(description = "사용자취향ID")
    private Long userPrefId;

    @Schema(description = "맛 리스트")
    private List<String> flavors;

    @Schema(description = "출처 리스트")
    private List<String> sources;
}

