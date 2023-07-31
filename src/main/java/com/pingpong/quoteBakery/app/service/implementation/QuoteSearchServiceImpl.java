package com.pingpong.quoteBakery.app.service.implementation;

import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.persistence.QuoteRepository;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.service.QuoteSearchService;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuoteSearchServiceImpl implements QuoteSearchService {
    private final UserPrefService userPrefService;
    private final QuoteRepository quoteRepository;
    private final QuoteConverter quoteConverter;

    @Override
    public QuoteDto getRandomQuoteByUser(Long userId) {
        UserPrefDto userPrefDto = userPrefService.getUserPrefByUserId(userId);
        QuoteDto searchDto = quoteConverter.convertToGeneric(userPrefDto, QuoteDto.class);

        return this.getRandomQuote(searchDto);
    }

    @Override
    public QuoteDto getRandomQuote(QuoteDto searchDto) {
        QuoteDto quoteDto = quoteConverter.convertEntityToDto(quoteRepository.searchQuote(searchDto));
        quoteDto.setUserId(searchDto.getUserId()); // resource converter에서 좋아요/보관여부 세팅시에 필요

        return quoteDto;
    }
}
