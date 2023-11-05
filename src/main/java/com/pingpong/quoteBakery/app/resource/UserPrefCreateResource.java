package com.pingpong.quoteBakery.app.resource;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPrefCreateResource {
    @Hidden // Hide this operation from Swagger UI
    @Schema(description = "사용자ID")
    private Long userId;
    @Schema(description = "맛 리스트")
    private List<String> flavors;

    @Schema(description = "출처 리스트")
    private List<String> sources;
}

