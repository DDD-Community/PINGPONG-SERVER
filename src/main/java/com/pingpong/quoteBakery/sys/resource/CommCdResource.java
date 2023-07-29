package com.pingpong.quoteBakery.sys.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class CommCdResource {

    @Schema(description = "공통코드ID")
    private Long commCdId;

    @Schema(description = "공통코드")
    private String commCd;

    @Schema(description = "공통코드명")
    private String commNm;
}

