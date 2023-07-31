package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteResource;
import com.pingpong.quoteBakery.app.service.QuoteSearchService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/mypage")
public class MyPageController {
    private final QuoteSearchService quoteSearchService;
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
        return quoteSearchService.getLikedQuotes(userId)
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
        return quoteSearchService.getScrapedQuotes(userId)
            .stream().map(dto -> quoteConverter.convertToGeneric(dto, QuoteResource.class)).collect(Collectors.toList());
    }
}
