/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import java.util.List;

public interface QuoteRepositoryCustom {
    /*
     * 맛,출처,상황 조건을 각 단일개만 가질 수 있는 조회 건
     * */
    Quote searchQuoteWithSingle(QuoteSingleSearchDto searchDto);
    /*
     * 맛,출처,상황 조건을 각각 여러개 가질 수 있는 조회 건
     * */
    Quote searchQuoteWithMulti(QuoteMultiSearchDto searchDto);
    List<Quote> searchQutes(QuoteMultiSearchDto searchDto);
}
