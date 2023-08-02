package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommCdDto {
    private Long commCdId;
    private String commCd;
    private String commNm;
    private CommCdTpDto commCdTp;
}

