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
            description = "uid 유무를 검사한다.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    @Parameter(name = "uid", description = "uid", in = ParameterIn.PATH)
    public boolean validateUid(@PathVariable("uid") String uid) {
        return userService.validateUid(uid);
    }

    @GetMapping("/validate-nick-name/{nickName}")
    @Operation(summary = "nickName 중복 유효성 검사",
            description = "닉네임 중복 유효성을 검사한다.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    @Parameter(name = "nickName", description = "닉네임", in = ParameterIn.PATH)
    public boolean validateNickName(@PathVariable("nickName") String nickName) {
        return userService.validateNickName(nickName);
    }
}
