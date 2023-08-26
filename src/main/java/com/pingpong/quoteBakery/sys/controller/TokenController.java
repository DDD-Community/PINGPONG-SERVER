package com.pingpong.quoteBakery.sys.controller;

import com.pingpong.quoteBakery.com.api.response.ApiRes;
import com.pingpong.quoteBakery.sys.dto.CreateAccessTokenRequestDto;
import com.pingpong.quoteBakery.sys.dto.CreateAccessTokenResponseDto;
import com.pingpong.quoteBakery.sys.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/token")
    public ApiRes<CreateAccessTokenResponseDto> createNewAccessToken(@RequestBody CreateAccessTokenRequestDto request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ApiRes.createSuccess(new CreateAccessTokenResponseDto(newAccessToken));
    }
}
