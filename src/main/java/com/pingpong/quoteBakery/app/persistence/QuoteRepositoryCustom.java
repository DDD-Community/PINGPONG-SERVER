/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
package com.pingpong.quoteBakery.app.persistence;

import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import java.util.List;

public interface QuoteRepositoryCustom {
    Quote searchQuote(QuoteDto searchDto);
    List<Quote> searchQutes(QuoteDto searchDto);
}
