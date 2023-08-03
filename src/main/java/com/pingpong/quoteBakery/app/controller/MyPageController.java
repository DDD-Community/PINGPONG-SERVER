package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteResource;
import com.pingpong.quoteBakery.app.resource.UserPrefResource;
import com.pingpong.quoteBakery.app.resource.UserPrefUpdateResource;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.sys.resource.CommCdTpResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/mypage")
public class MyPageController {
    private final QuoteService quoteService;
    private final UserPrefService userPrefService;
    private  final QuoteConverter quoteConverter;

    /**
     * 마이페이지 좋아요 목록 조회
     */
    @GetMapping("/likes/{userId}")
    @Operation(summary = "마이페이지에서 좋아요 목록 조회",
            description  = "마이페이지 좋아요한 명언 목록 조회",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public List<QuoteResource> getLikedQuotes(@PathVariable("userId") Long userId){
        return quoteService.getLikedQuotes(userId)
            .stream().map(dto -> quoteConverter.convertToGeneric(dto, QuoteResource.class)).collect(Collectors.toList());
    }

    /**
     * 마이페이지 스크랩 목록 조회
     */
    @GetMapping("/scraps/{userId}")
    @Operation(summary = "마이페이지에서 스크랩 목록 조회",
        description  = "마이페이지 보관한 명언 목록 조회",
        responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public List<QuoteResource> getScrapedQuotes(@PathVariable("userId") Long userId){
        return quoteService.getScrapedQuotes(userId)
            .stream().map(dto -> quoteConverter.convertToGeneric(dto, QuoteResource.class)).collect(Collectors.toList());
    }

    /**
     * 사용자 취향 조회
     */
    @GetMapping("/user-pref/{userId}")
    @Operation(summary = "사용자 취향 조회",
        description  = "온보딩에서 저장한 사용자별 취향을 마이페이지에서 조회한다.",
        responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = UserPrefResource.class)))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public UserPrefResource getUserPref(@PathVariable("userId") Long userId){
        return quoteConverter.convertToGeneric(userPrefService.getUserPrefByUserId(userId), UserPrefResource.class);
    }

    /**
     * 사용자 취향 수정
     */
    @PutMapping("/user-pref/{userId}")
    @Operation(summary = "사용자 취향 수정", description  = "사용자의 취향정보를 수정 한다.",
        responses = {@ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(type = "number", description = "사용자취향ID")))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public Long saveUserPref(@PathVariable("userId") Long userId,
        @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UserPrefUpdateResource updateResource){
        return userPrefService.updateUserPref(quoteConverter.convertToGeneric(updateResource, UserPrefDto.class));
    }
}
