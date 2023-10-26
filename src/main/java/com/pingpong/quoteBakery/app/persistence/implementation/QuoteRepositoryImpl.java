/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
package com.pingpong.quoteBakery.app.persistence.implementation;

import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import com.pingpong.quoteBakery.app.persistence.QuoteRepositoryCustom;
import com.pingpong.quoteBakery.com.entity.QueryDslSupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.pingpong.quoteBakery.app.domain.QQuote.quote;

@Repository
public class QuoteRepositoryImpl extends QueryDslSupport implements QuoteRepositoryCustom {
    public QuoteRepositoryImpl() {
        super(Quote.class);
    }

    private BooleanBuilder makeBlnBldrSingle(QuoteSingleSearchDto searchDto) {
        BooleanBuilder builder = new BooleanBuilder();

        if (searchDto.getSource() != null && !searchDto.getSource().isEmpty()) {
            builder.and(quote.source.eq(searchDto.getSource()));
        }
        if (searchDto.getFlavor() != null && !searchDto.getFlavor().isEmpty()) {
            builder.and(quote.flavor.eq(searchDto.getFlavor()));
        }
        if (searchDto.getMood() != null && !searchDto.getMood().isEmpty()) {
            builder.and(quote.mood.eq(searchDto.getMood()));
        }

        return builder;
    }

    private BooleanBuilder makeBlnBldrMulti(QuoteMultiSearchDto searchDto) {
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
    public Quote searchQuoteWithMulti(QuoteMultiSearchDto searchDto) {
        return makeRandomSearchQuote(this.makeBlnBldrMulti(searchDto));
    }

    @Override
    public Quote searchQuoteWithSingle(QuoteSingleSearchDto searchDto) {
        return makeRandomSearchQuote(this.makeBlnBldrSingle(searchDto));
    }

    private Quote makeRandomSearchQuote(BooleanBuilder builder){
        return getQueryFactory().selectFrom(quote).where(builder).orderBy(NumberExpression.random().asc()).fetchFirst();
    }

    @Override
    public Page<Quote> searchQuotes(QuoteMultiSearchDto searchDto, Pageable pageable) {
        return applyPagination(pageable, query -> makeSearchQuery(searchDto));
    }

    private JPAQuery<Quote> makeSearchQuery(QuoteMultiSearchDto searchDto){
        BooleanBuilder builder = this.makeBlnBldrMulti(searchDto);

        return getQueryFactory().selectFrom(quote)
                .where(builder)
                .orderBy(createOrderSpecifier(quote.content, searchDto.getOrderBy()));
    }

    private OrderSpecifier<?> createOrderSpecifier(StringPath sortProperty, String orderBy) {
        return "DESC".equalsIgnoreCase(orderBy) ?
            new OrderSpecifier<>(Order.DESC, sortProperty) : new OrderSpecifier<>(Order.ASC, sortProperty);
    }
}
