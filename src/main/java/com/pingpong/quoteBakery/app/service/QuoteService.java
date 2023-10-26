package com.pingpong.quoteBakery.app.service;

import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {
    /**
     * 사용자 취향에 맞춘 랜덤 명언조회
     * @param userId, pageable
     * @return QuoteDto
     */
    Page<QuoteDto> searchRandomQuotesByUser(Long userId, Pageable pageable);

    /**
     * 맛,출처,상황을 각각 여러개 갖는 조건에 맞는 랜덤 명언 조회
     * @param searchDto 
     * @return QuoteDto
     */
    QuoteDto getRandomQuoteWithMulti(QuoteMultiSearchDto searchDto);

    /**
     * 맛,출처,상황을 각 단 한개만 갖는 조건에 맞는 랜덤 명언 조회
     * @param searchDto
     * @return QuoteDto
     */
    QuoteDto getRandomQuoteWithSingle(QuoteSingleSearchDto searchDto);
    

    List<QuoteDto> getLikedQuotes(Long userId);

    Page<QuoteDto> searchQuotePages(QuoteMultiSearchDto searchDto, Pageable pageable);

    /**
     * @param likeDto
     * @return likeId
     */
    Long saveLike(LikeDto likeDto);
}

