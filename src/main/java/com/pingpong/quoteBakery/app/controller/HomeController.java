package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import com.pingpong.quoteBakery.app.resource.LikeResource;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.RandomQuoteResource;
import com.pingpong.quoteBakery.app.resource.RandomQuoteSearchPageResource;
import com.pingpong.quoteBakery.app.resource.RandomQuoteSearchResource;
import com.pingpong.quoteBakery.app.resource.ScrapResource;
import com.pingpong.quoteBakery.app.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<RandomQuoteResource> getRandomQuoteByUserId(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody RandomQuoteSearchPageResource searchPageResource){

        return quoteService.searchRandomQuotesByUser(searchPageResource.getUserId(), searchPageResource.getPageInfo())
                .map(quoteConverter::convertDtoToRandomResource);
    }

    /**
     * 홈 화면 랜덤 명언 굽기
     */
    @GetMapping("/bake-quote")
    @Operation(summary = "홈 화면 랜덤 명언 굽기",
        description  = "홈 화면 랜덤 명언 굽기",
        responses = { @ApiResponse(responseCode = "200", description = "굽기 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public RandomQuoteResource bakeRandomQuote(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody RandomQuoteSearchResource searchResource){

        return quoteConverter.convertDtoToRandomResource(
            quoteService.getRandomQuoteWithSingle(quoteConverter.convertToGeneric(searchResource, QuoteSingleSearchDto.class)));
    }

    /**
     * 명언 좋아요
     */
    @PostMapping("/like")
    @Operation(summary = "명언 좋아요", description  = "명언 좋아요",
        responses = {@ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(schema = @Schema(type = "number", description = "좋아요ID")))}
    )
    public Long likeQuote(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody LikeResource createResource){
        return quoteService.saveLike(quoteConverter.convertToGeneric(createResource, LikeDto.class));
    }

    /**
     * 명언 보관
     */
    @PostMapping("/scrap")
    @Operation(summary = "명언 스크랩", description  = "명언 스크랩",
        responses = {@ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(schema = @Schema(type = "number", description = "스크랩ID")))}
    )
    public Long scrapQuote(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody ScrapResource createResource){
        return quoteService.saveScrap(quoteConverter.convertToGeneric(createResource, ScrapDto.class));
    }
}

