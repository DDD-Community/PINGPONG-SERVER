package com.pingpong.quoteBakery.sys.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommCdTpDto {
    private Long commCdTpId;
    private String commCdTpCd;
    private String commCdTpNm;

    private List<CommCdDto> commCds;
}

