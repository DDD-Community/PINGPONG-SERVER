package com.pingpong.quoteBakery.sys.controller;

import com.pingpong.quoteBakery.com.api.response.ApiRes;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.dto.FBUserRequestDto;
import com.pingpong.quoteBakery.sys.dto.WithdrawalDto;
import com.pingpong.quoteBakery.sys.resource.UserResource;
import com.pingpong.quoteBakery.sys.resource.UserWithdrawalResource;
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
    private final CommonConverter commonConverter;

    @PostMapping("/signup")
    public ApiRes<UserResource> signup(@RequestBody FBUserRequestDto requestDto) {
        return ApiRes.createSuccess(commonConverter.convertToGeneric(userService.saveByFireBase(requestDto), UserResource.class));
    }

    @GetMapping("/validate-uid/{uid}")
    @Operation(summary = "uid 유무 검사",
            description = "uid가 DB에 저장된 경우 TRUE, uid가 없을 경우 FALSE.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    @Parameter(name = "uid", description = "uid", in = ParameterIn.PATH)
    public ApiRes<Boolean> validateUid(@PathVariable("uid") String uid) {
        return ApiRes.createSuccess(userService.validateUid(uid));
    }

    @GetMapping("/validate-nickname")
    @Operation(summary = "nickname 중복 유효성 검사",
            description = "닉네임이 중복된 경우 FALSE, 중복이 아닌 경우 TRUE.",
            responses = {@ApiResponse(responseCode = "200", description = "검증 성공")}
    )
    public ApiRes<Boolean> validateNickname(@Parameter(description = "닉네임") @RequestParam("nickname") String nickname) {
        return ApiRes.createSuccess(userService.validateNickname(nickname));
    }

    @GetMapping("/search-user-by-uid/{uid}")
    @Operation(summary = "uid로 유저 조회",
            description = "소셜로그인 사용시 uid로 사용자를 조회한다",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공")}
    )
    @Parameter(name = "uid", description = "uid", in = ParameterIn.PATH)
    public ApiRes<UserResource> searchUserByUid(@PathVariable("uid") String uid) {
        return ApiRes.createSuccess(commonConverter.convertToGeneric(userService.findByUid(uid), UserResource.class));
    }

    @GetMapping("/search-user-by-id/{id}")
    @Operation(summary = "id로 유저 조회",
            description = "소셜로그인 사용시 id로 사용자를 조회한다",
            responses = {@ApiResponse(responseCode = "200", description = "조회 성공")}
    )
    @Parameter(name = "id", description = "id", in = ParameterIn.PATH)
    public ApiRes<UserResource> searchUserByUid(@PathVariable("id") Long id) {
        return ApiRes.createSuccess(commonConverter.convertToGeneric(userService.findById(id), UserResource.class));
    }

    /*
    * 회원 탈퇴
    * */
    @PostMapping("/withdrawal")
    public void signup(@RequestBody UserWithdrawalResource withdrawalResource) {
        userService.withdrawalAccount(commonConverter.convertToGeneric(withdrawalResource, WithdrawalDto.class));
    }
}
