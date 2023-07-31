package com.pingpong.quoteBakery.app.dto;

import lombok.Data;


@Data
public class UserPrefDto {
    private Long userPrefId;

    private String flavor;

    private String source;

    private Long userId;
}

