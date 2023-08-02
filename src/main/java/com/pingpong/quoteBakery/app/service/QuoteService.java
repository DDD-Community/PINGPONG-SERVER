package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import java.util.List;

public interface QuoteService {
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

    List<QuoteDto> getLikedQuotes(Long userId);

    List<QuoteDto> getScrapedQuotes(Long userId);

    List<QuoteDto> searchQuotes(QuoteDto searchDto);

    /**
     * @param likeDto
     * @return
     */
    Long saveLike(LikeDto likeDto);

    /**
     * @param scrapDto
     * @return
     */
    Long saveScrap(ScrapDto scrapDto);
}

