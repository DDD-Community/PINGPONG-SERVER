package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteSearchResource;
import com.pingpong.quoteBakery.app.resource.RandomQuoteResource;
import com.pingpong.quoteBakery.app.service.QuoteSearchService;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/home")
public class HomeController {
    private final QuoteSearchService quoteSearchService;
    private final QuoteConverter quoteConverter;

    /**
     * 홈 화면 랜덤 명언 조회
     */
    @GetMapping("/random-quote/{userId}")
    @Operation(summary = "홈 화면 랜덤 명언 조회",
            description  = "홈 화면 랜덤 명언 조회",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public RandomQuoteResource getRandomQuoteByUserId(@PathVariable("userId") Long userId){

        return quoteConverter.convertDtoToRandomResource(quoteSearchService.getRandomQuoteByUser(userId));
    }

    /**
     * 홈 화면 랜덤 명언 굽기
     */
    @GetMapping("/bake-quote")
    @Operation(summary = "홈 화면 랜덤 명언 굽기",
        description  = "홈 화면 랜덤 명언 굽기",
        responses = { @ApiResponse(responseCode = "200", description = "굽기 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public RandomQuoteResource bakeRandomQuote(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody QuoteSearchResource searchResource){

        return quoteConverter.convertDtoToRandomResource(quoteSearchService.getRandomQuote(quoteConverter.convertToGeneric(searchResource, QuoteDto.class)));
    }

}
