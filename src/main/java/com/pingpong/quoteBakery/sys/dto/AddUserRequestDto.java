package com.pingpong.quoteBakery.sys.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddUserRequestDto {
    private String email;
    private String password;
    private String nickName;
    private String jobCd;
    private String desc;
}
