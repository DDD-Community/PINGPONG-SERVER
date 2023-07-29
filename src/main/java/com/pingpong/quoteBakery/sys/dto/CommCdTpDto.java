package com.pingpong.quoteBakery.sys.dto;

import lombok.Data;

import java.util.Set;


@Data
public class CommCdTpDto {
    private Long commCdTpId;
    private String commCdTpCd;
    private String commCdTpNm;

    private Set<CommCdDto> commCds;
}

