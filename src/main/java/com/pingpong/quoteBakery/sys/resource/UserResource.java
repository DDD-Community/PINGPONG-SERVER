package com.pingpong.quoteBakery.sys.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResource {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "firebase id")
    private String uid;

    @Schema(description = "fcm token")
    private String fcm;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "닉네임")
    private String nickname;

    @Schema(description = "직업코드")
    private String jobCd;

    @Schema(description = "사용자별 설명")
    private String rmk;
}
