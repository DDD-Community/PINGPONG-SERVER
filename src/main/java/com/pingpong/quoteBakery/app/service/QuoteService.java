package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
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
     * 맛,출처,상황을 각각 여러개 갖는 조건에 맞는 랜덤 명언 조회
     * @param searchDto 
     * @return
     */
    QuoteDto getRandomQuoteWithMulti(QuoteMultiSearchDto searchDto);

    /**
     * 맛,출처,상황을 각 단 한개만 갖는 조건에 맞는 랜덤 명언 조회
     * @param searchDto
     * @return
     */
    QuoteDto getRandomQuoteWithSingle(QuoteSingleSearchDto searchDto);
    

    List<QuoteDto> getLikedQuotes(Long userId);

    List<QuoteDto> getScrapedQuotes(Long userId);

    List<QuoteDto> searchQuotes(QuoteMultiSearchDto searchDto);

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

