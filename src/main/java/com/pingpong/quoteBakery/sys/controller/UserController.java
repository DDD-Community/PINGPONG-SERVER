package com.pingpong.quoteBakery.sys.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.pingpong.quoteBakery.com.api.response.ApiRes;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.dto.FBUserRequestDto;
import com.pingpong.quoteBakery.sys.dto.UserDto;
import com.pingpong.quoteBakery.sys.dto.WithdrawalDto;
import com.pingpong.quoteBakery.sys.resource.UserResource;
import com.pingpong.quoteBakery.sys.resource.UserUpdateResource;
import com.pingpong.quoteBakery.sys.resource.UserWithdrawalResource;
import com.pingpong.quoteBakery.sys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final CommonConverter commonConverter;

    @PostMapping("/signup")
    public ApiRes<UserResource> signup(@RequestBody FBUserRequestDto requestDto) throws FirebaseAuthException {
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
    @PostMapping("/withdrawal/{userId}")
    @Operation(summary = "회원 탈퇴",
            description = "회원 정보 삭제 및 탈퇴 사유를 저장한다.",
            responses = {@ApiResponse(responseCode = "200", description = "탈퇴 성공")}
    )
    @Parameter(name = "userId", description = "userId", in = ParameterIn.PATH)
    public ApiRes<?> signup(@PathVariable("userId") Long userId, @RequestBody UserWithdrawalResource withdrawalResource) {
        withdrawalResource.setUserId(userId);
        userService.withdrawalAccount(commonConverter.convertToGeneric(withdrawalResource, WithdrawalDto.class));
        return ApiRes.createSuccessWithNoContent();
    }

    /*
     * 사용자 정보 수정
     *  */
    @PutMapping("/user-info/{userId}")
    @Operation(summary = "사용자 정보 수정", description  = "사용자의 정보를 수정 한다.",
            responses = {@ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(type = "number", description = "사용자ID")))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public ApiRes<?> updateUserInfo(@PathVariable("userId") Long userId,
    @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UserUpdateResource userResource){
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setNickname(userResource.getNickname());
        userService.updateUserInfo(userDto);

        return ApiRes.createSuccessWithNoContent();
    }
}
