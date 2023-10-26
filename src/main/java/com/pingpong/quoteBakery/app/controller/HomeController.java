package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import com.pingpong.quoteBakery.app.resource.*;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.com.api.response.ApiRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/home")
public class HomeController {
    private final QuoteService quoteService;
    private final QuoteConverter quoteConverter;

    /**
     * 홈 화면 랜덤 명언 조회
     */
    @GetMapping("/random-quote")
    @Operation(summary = "홈 화면 랜덤 명언 목록 조회",
            description  = "홈 화면 랜덤 명언 목록 조회",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public ApiRes<Page<RandomQuoteResource>> getRandomQuoteByUserId(
            @Parameter(description = "페이지 번호") @RequestParam("page") int page,
            @Parameter(description = "페이지 당 항목 수") @RequestParam("sizePerPage") int sizePerPage,
            @Parameter(description = "사용자 ID") @RequestParam("userId") Long userId
    ){

        return ApiRes.createSuccess(quoteService.searchRandomQuotesByUser(userId, PageRequest.of(page, sizePerPage))
                .map(quote -> quoteConverter.convertDtoToRandomResource(quote, userId)));
    }

    /**
     * 홈 화면 랜덤 명언 굽기
     */
    @GetMapping("/bake-quote")
    @Operation(summary = "홈 화면 랜덤 명언 굽기",
            description  = "홈 화면 랜덤 명언 굽기",
            responses = { @ApiResponse(responseCode = "200", description = "굽기 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public ApiRes<RandomQuoteResource> bakeRandomQuote(
            @Parameter(description = "사용자ID") @RequestParam(value="userId", required=false) Long userId,
            @Parameter(description = "맛") @RequestParam(value="flavor", required=false) String flavor,
            @Parameter(description = "출처") @RequestParam(value="source", required=false) String source,
            @Parameter(description = "상황") @RequestParam(value="mood", required=false) String mood) {

        RandomQuoteSearchResource searchResource = new RandomQuoteSearchResource();
        searchResource.setUserId(userId);
        searchResource.setFlavor(flavor);
        searchResource.setSource(source);
        searchResource.setMood(mood);

        return ApiRes.createSuccess(quoteConverter.convertDtoToRandomResource(
                quoteService.getRandomQuoteWithSingle(quoteConverter.convertToGeneric(searchResource, QuoteSingleSearchDto.class)), userId));
    }

    /**
     * 명언 좋아요
     */
    @PostMapping("/like")
    @Operation(summary = "명언 좋아요", description  = "명언 좋아요",
        responses = {@ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(schema = @Schema(type = "number", description = "좋아요ID")))}
    )
    public ApiRes<Long> likeQuote(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody LikeCreateResource createResource){
        return ApiRes.createSuccess(quoteService.saveLike(quoteConverter.convertToGeneric(createResource, LikeDto.class)));
    }
}

