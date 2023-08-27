package com.pingpong.quoteBakery.sys.controller;

import com.pingpong.quoteBakery.sys.dto.AddUserRequestDto;
import com.pingpong.quoteBakery.sys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody AddUserRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
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
