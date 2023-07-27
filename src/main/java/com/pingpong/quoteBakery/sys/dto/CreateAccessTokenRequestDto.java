package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestDto {
    private String refreshToken;
}
