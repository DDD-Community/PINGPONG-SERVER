package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String uid;
    private String fcm;
    private String email;
    private String nickname;
    private String jobCd;
    private String rmk;
}
