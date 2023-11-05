package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserPrefUpdateResource {
    @Schema(description = "사용자취향ID", requiredMode= RequiredMode.REQUIRED)
    private Long userPrefId;

    @Schema(description = "맛 리스트")
    private List<String> flavors;

    @Schema(description = "출처 리스트")
    private List<String> sources;
}

