package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.service.OnBoardService;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import com.pingpong.quoteBakery.sys.resource.CommCdTpResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/onboard")
public class OnBoardController {
    private final OnBoardService onBoardService;
    private final CommCdConverter converter;

    /**
     * 온보딩에서 사용할 사용자 취향 공통코드 조회
     */
    @GetMapping("/search-user-pref-codes")
    @Operation(summary = "온보딩에서 사용할 사용자 취향 관련 공통코드 조회",
            description  = "사용자 취향 관련한 명언 공통코드 맛/출처 조회",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    public List<CommCdTpResource> searchCommCds(){
        return onBoardService.searchUserPrefCode().stream().map(converter::convertDtoToResource).collect(Collectors.toList());
    }
}
