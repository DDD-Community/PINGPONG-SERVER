package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteResource;
import com.pingpong.quoteBakery.app.resource.UserPrefResource;
import com.pingpong.quoteBakery.app.resource.UserPrefUpdateResource;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.com.api.response.ApiRes;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @Operation(summary = "마이페이지에서 좋아요 목록 조회(보관함조회)",
            description  = "마이페이지 좋아요한 명언 목록 조회(보관함조회)",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public ApiRes<List<QuoteResource>> getLikedQuotes(@PathVariable("userId") Long userId){
        return ApiRes.createSuccess(quoteService.getLikedQuotes(userId)
            .stream().map(dto -> quoteConverter.convertToGeneric(dto, QuoteResource.class)).collect(Collectors.toList()));
    }

    /**
     * 마이페이지 좋아요 삭제
     */
    @DeleteMapping("/like/{likeId}")
    @Operation(summary = "마이페이지에서 좋아요 단건 삭제(보관 삭제)",
            description  = "마이페이지 좋아요 단건 삭제(보관 삭제)",
            responses = { @ApiResponse(responseCode = "200", description = "삭제 성공")}
    )
    @Parameter(name = "likeId", description = "좋아요(보관)ID", in = ParameterIn.PATH)
    public ApiRes<?> deleteLike(@PathVariable("likeId") Long likeId){
        quoteService.deleteLike(likeId);
        return ApiRes.createSuccessWithNoContent();
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
    public ApiRes<UserPrefResource> getUserPref(@PathVariable("userId") Long userId){
        return ApiRes.createSuccess(quoteConverter.convertToGeneric(userPrefService.getUserPrefByUserId(userId), UserPrefResource.class));
    }

    /**
     * 사용자 취향 수정
     */
    @PutMapping("/user-pref/{userId}")
    @Operation(summary = "사용자 취향 수정", description  = "사용자의 취향정보를 수정 한다.",
        responses = {@ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(type = "number", description = "사용자취향ID")))}
    )
    @Parameter(name = "userId", description = "사용자ID", in = ParameterIn.PATH)
    public ApiRes<Long> saveUserPref(@PathVariable("userId") Long userId,
        @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody UserPrefUpdateResource updateResource){
        return ApiRes.createSuccess(userPrefService.updateUserPref(quoteConverter.convertToGeneric(updateResource, UserPrefDto.class)));
    }
}
