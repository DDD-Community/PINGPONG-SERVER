package com.pingpong.quoteBakery.sys.dto;

import lombok.Data;

import java.util.List;


@Data
public class CommCdTpDto {
    private Long commCdTpId;
    private String commCdTpCd;
    private String commCdTpNm;

    private List<CommCdDto> commCds;
}

