package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.resource.UserPrefCreateResource;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import com.pingpong.quoteBakery.sys.resource.CommCdTpResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/onboard")
public class  OnBoardController {
    private final UserPrefService userPrefService;
    private final CommCdConverter cdConverter;

    /**
     * 사용자 취향 공통코드 조회
     */
    @GetMapping("/search-user-pref-codes")
    @Operation(summary = "온보딩에서 사용할 사용자 취향 관련 공통코드 조회",
            description  = "사용자 취향 관련한 명언 공통코드 맛/출처 조회",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    public List<CommCdTpResource> searchCommCds(){
        return userPrefService.searchUserPrefCode().stream().map(cdConverter::convertDtoToResource).collect(Collectors.toList());
    }

    /**
     * 사용자 취향 저장
     */
    @PostMapping("/user-pref/{userId}")
    @Operation(summary = "사용자 취향 등록", description  = "userId로 사용자 취향을 등록한다.",
        responses = {@ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(schema = @Schema(type = "number", description = "사용자취향ID")))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public Long saveUserPref(@PathVariable("userId") Long userId,
        @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UserPrefCreateResource createResource){
        createResource.setUserId(userId);
        return userPrefService.createUserPref(cdConverter.convertToGeneric(createResource, UserPrefDto.class));
    }
}
