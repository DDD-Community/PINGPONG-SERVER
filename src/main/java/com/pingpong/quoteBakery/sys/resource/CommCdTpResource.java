package com.pingpong.quoteBakery.sys.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommCdTpResource {

    @Schema(description = "공통코드유형코드ID")
    private Long commCdTpId;

    @Schema(description = "공통코드유형코드")
    private String commCdTpCd;

    @Schema(description = "공통코드유형명")
    private String commCdTpNm;

    @Schema(description = "코드리스트")
    private List<CommCdResource> commCds;
}

