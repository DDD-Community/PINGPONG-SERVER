/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
package com.pingpong.quoteBakery.app.persistence.implementation;

import static com.pingpong.quoteBakery.app.domain.QQuote.quote;

import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.persistence.QuoteRepositoryCustom;
import com.pingpong.quoteBakery.com.entity.QueryDslSupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepositoryImpl extends QueryDslSupport implements QuoteRepositoryCustom {
    public QuoteRepositoryImpl() {
        super(Quote.class);
    }

    private BooleanBuilder makeBlnBldr(QuoteDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (searchDto.getSource() != null) {
            builder.and(quote.source.eq(searchDto.getSource()));
        }
        if (searchDto.getFlavor() != null) {
            builder.and(quote.flavor.eq(searchDto.getFlavor()));
        }
        if (searchDto.getMood() != null) {
            builder.and(quote.mood.eq(searchDto.getMood()));
        }
        if (!StringUtils.isEmpty(searchDto.getKeyword())) {
            builder.and(quote.content.like(searchDto.getKeyword()));
        }

        return builder;
    }

    @Override
    public Quote searchQuote(QuoteDto searchDto) {
        BooleanBuilder builder = this.makeBlnBldr(searchDto);

        return getQueryFactory().selectFrom(quote).where(builder).orderBy(NumberExpression.random().asc()).fetchFirst();
    }
}
