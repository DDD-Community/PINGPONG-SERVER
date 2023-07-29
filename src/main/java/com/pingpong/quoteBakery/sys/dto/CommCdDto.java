package com.pingpong.quoteBakery.sys.dto;

import lombok.Data;


@Data
public class CommCdDto {
    private Long commCdId;
    private String commCd;
    private String commNm;
    private CommCdTpDto commCdTp;
}

