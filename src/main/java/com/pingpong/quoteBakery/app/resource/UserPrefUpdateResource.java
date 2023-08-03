package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.com.resource.BaseResource;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPrefUpdateResource extends BaseResource {
    @Schema(description = "사용자취향ID", requiredMode= RequiredMode.REQUIRED)
    private Long userPrefId;

    @Schema(description = "맛 리스트")
    private List<String> flavors;

    @Schema(description = "출처 리스트")
    private List<String> sources;
}

