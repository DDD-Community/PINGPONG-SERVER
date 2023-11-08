package com.pingpong.quoteBakery.sys.resource;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserWithdrawalResource {
    @Hidden
    @Schema(description = "사용자ID")
    private Long userId;

    @Schema(description = "탈퇴사유")
    private String reason;
}
