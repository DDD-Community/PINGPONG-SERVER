package com.pingpong.quoteBakery.sys.controller;

import com.pingpong.quoteBakery.sys.dto.FBUserRequestDto;
import com.pingpong.quoteBakery.sys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody FBUserRequestDto requestDto) {
        return userService.saveByFireBase(requestDto);
    }

    @GetMapping("/validate-uid/{uid}")
    @Operation(summary = "uid 유무 검사",
            description = "uid가 DB에 저장된 경우 TRUE, uid가 없을 경우 FALSE.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    @Parameter(name = "uid", description = "uid", in = ParameterIn.PATH)
    public boolean validateUid(@PathVariable("uid") String uid) {
        return userService.validateUid(uid);
    }

    @GetMapping("/validate-nickname")
    @Operation(summary = "nickname 중복 유효성 검사",
            description = "닉네임이 중복된 경우 FALSE, 중복이 아닌 경우 TRUE.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    public boolean validateNickname(@Parameter(description = "닉네임") @RequestParam("nickname") String nickname) {
        return userService.validateNickname(nickname);
    }
}
