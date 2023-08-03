package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteResource;
import com.pingpong.quoteBakery.app.resource.QuoteSearchResource;
import com.pingpong.quoteBakery.app.resource.RandomQuoteResource;
import com.pingpong.quoteBakery.app.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/search")
public class SearchController {
    private final QuoteService quoteService;
    private final QuoteConverter quoteConverter;

    /**
     * 명언탐색
     */
    @GetMapping("/quote")
    @Operation(summary = "명언 탐색",
            description  = "조건에 맞게 명언을 탐색한다",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = RandomQuoteResource.class)))}
    )
    public List<QuoteResource> searchQuotes(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody QuoteSearchResource searchResource){

        return quoteService.searchQuotes(quoteConverter.convertToGeneric(searchResource, QuoteMultiSearchDto.class))
            .stream().map(dto -> quoteConverter.convertToGeneric(dto, QuoteResource.class))
            .collect(Collectors.toList());
    }
    
}

