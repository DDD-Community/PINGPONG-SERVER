package com.pingpong.quoteBakery.app.controller;

import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.resource.QuoteResource;
import com.pingpong.quoteBakery.app.resource.QuoteSearchResource;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.com.api.response.ApiRes;
import com.pingpong.quoteBakery.sys.dto.UserDto;
import com.pingpong.quoteBakery.sys.service.TokenService;
import com.pingpong.quoteBakery.sys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final TokenService tokenService;
    private final UserService userService;

    /**
     * 명언탐색
     */
    @PostMapping("/quote")
    @Operation(summary = "명언 탐색", description  = "조건에 맞게 명언을 탐색한다",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = QuoteResource.class)))}
    )
    public ApiRes<Page<QuoteResource>> searchQuotes(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody QuoteSearchResource searchResource){
        UserDto userDto = userService.findByUid(tokenService.getCurrentTokenInfo().getUid());
        return ApiRes.createSuccess(quoteService.searchQuotePages(quoteConverter.convertToGeneric(searchResource, QuoteMultiSearchDto.class), searchResource.getPageInfo())
                .map(quote -> quoteConverter.convertDtoToRandomResource(quote, userDto.getId())));
    }
}

