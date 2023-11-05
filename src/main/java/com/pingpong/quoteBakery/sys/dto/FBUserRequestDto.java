package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FBUserRequestDto {
    private String token;
    private String fcm;
    private String email;
    private String nickname;
    private String jobCd;
}
