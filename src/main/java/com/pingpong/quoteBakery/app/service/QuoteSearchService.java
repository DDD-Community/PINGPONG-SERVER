package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.app.dto.QuoteDto;

public interface QuoteSearchService {
    /**
     * 사용자 취향에 맞춘 랜덤 명언조회
     * DELEGATE ONLY
     * @param userId
     * @return
     */
    QuoteDto getRandomQuoteByUser(Long userId);

    /**
     * 조건에 맞는 랜덤 명언 조회
     * @param searchDto 
     * @return
     */
    QuoteDto getRandomQuote(QuoteDto searchDto);
}

