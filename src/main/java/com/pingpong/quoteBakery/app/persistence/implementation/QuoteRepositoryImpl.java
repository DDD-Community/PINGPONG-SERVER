/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
package com.pingpong.quoteBakery.app.persistence.implementation;

import static com.pingpong.quoteBakery.app.domain.QQuote.quote;
import static com.querydsl.core.types.ExpressionUtils.orderBy;

import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.persistence.QuoteRepositoryCustom;
import com.pingpong.quoteBakery.com.entity.QueryDslSupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepositoryImpl extends QueryDslSupport implements QuoteRepositoryCustom {
    public QuoteRepositoryImpl() {
        super(Quote.class);
    }

    private BooleanBuilder makeBlnBldr(QuoteDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (searchDto.getSources() != null && !searchDto.getSources().isEmpty()) {
            builder.and(quote.source.in(searchDto.getSources()));
        }
        if (searchDto.getFlavors() != null && !searchDto.getFlavors().isEmpty()) {
            builder.and(quote.flavor.in(searchDto.getFlavors()));
        }
        if (searchDto.getMoods() != null && !searchDto.getMoods().isEmpty()) {
            builder.and(quote.mood.in(searchDto.getMoods()));
        }
        if (!StringUtils.isEmpty(searchDto.getKeyword())) {
            BooleanExpression keywordInContent = quote.content.containsIgnoreCase(searchDto.getKeyword());
            BooleanExpression keywordInAuthor = quote.author.containsIgnoreCase(searchDto.getKeyword());
            builder.and(keywordInContent.or(keywordInAuthor));
        }

        return builder;
    }

    @Override
    public Quote searchQuote(QuoteDto searchDto) {
        BooleanBuilder builder = this.makeBlnBldr(searchDto);

        return getQueryFactory().selectFrom(quote).where(builder).orderBy(NumberExpression.random().asc()).fetchFirst();
    }

    @Override
    public List<Quote> searchQutes(QuoteDto searchDto) {
        BooleanBuilder builder = this.makeBlnBldr(searchDto);

        return getQueryFactory().selectFrom(quote)
            .where(builder)
            .orderBy(createOrderSpecifier(quote.content, searchDto.getOrderBy()))
            .fetch();
    }

    private OrderSpecifier<?> createOrderSpecifier(StringPath sortProperty, String orderBy) {
        return "DESC".equalsIgnoreCase(orderBy) ?
            new OrderSpecifier<>(Order.DESC, sortProperty) : new OrderSpecifier<>(Order.ASC, sortProperty);
    }
}
