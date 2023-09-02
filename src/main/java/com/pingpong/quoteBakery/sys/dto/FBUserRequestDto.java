package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FBUserRequestDto {
    private String uid;
    private String fcm;
    private String email;
    private String nickName;
    private String jobCd;
}
