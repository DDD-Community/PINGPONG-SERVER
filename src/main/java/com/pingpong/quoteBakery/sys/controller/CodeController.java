package com.pingpong.quoteBakery.sys.controller;

import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import com.pingpong.quoteBakery.sys.resource.CommCdTpResource;
import com.pingpong.quoteBakery.sys.service.CommCdTpService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/code")
public class CodeController {

    private final CommCdTpService commCdTpService;
    private final CommCdConverter converter;

    /**
     * 공통코드유형코드 조회 by 유형코드명
     */
    @GetMapping("/saerch-comm-cd/{commCdTpCd}")
    @Operation(summary = "공통코드 조회",
            description  = "공통코드유형코드로 공통코드를 조회한다.",
            responses = { @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = CommCdTpResource.class)))}
    )
    @Parameter(name = "commCdTpCd", description = "공통코드유형코드", in = ParameterIn.PATH)
    public CommCdTpResource getCommCdTpByCd(@PathVariable("commCdTpCd") String commCdTpCd){
        return converter.convertDtoToResource(commCdTpService.getCommCdTpByCd(commCdTpCd));
    }
}
