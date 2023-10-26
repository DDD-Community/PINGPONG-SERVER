package com.pingpong.quoteBakery.app.service.implementation;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.*;
import com.pingpong.quoteBakery.app.persistence.LikeRepository;
import com.pingpong.quoteBakery.app.persistence.QuoteRepository;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final UserPrefService userPrefService;
    private final UserService userService;
    private final QuoteRepository quoteRepository;
    private final LikeRepository likeRepository;
    private final QuoteConverter quoteConverter;

    @Override
    public Page<QuoteDto> searchRandomQuotesByUser(Long userId, Pageable pageable) {
        UserPrefDto userPrefDto = userPrefService.getUserPrefByUserId(userId);
        QuoteMultiSearchDto searchDto = quoteConverter.convertToGeneric(userPrefDto, QuoteMultiSearchDto.class);
        List<QuoteDto> quoteDtos = this.searchQuotes(searchDto);

        return new PageImpl<>(quoteDtos, pageable, quoteDtos.size());
    }

    @Override
    public QuoteDto getRandomQuoteWithMulti(QuoteMultiSearchDto searchDto) {
        return quoteConverter.convertEntityToDto(quoteRepository.searchQuoteWithMulti(searchDto));
    }


    @Override
    public QuoteDto getRandomQuoteWithSingle(QuoteSingleSearchDto searchDto) {
        return quoteConverter.convertEntityToDto(quoteRepository.searchQuoteWithSingle(searchDto));
    }

    @Override
    public List<QuoteDto> getLikedQuotes(Long userId) {

        return likeRepository.findAllByUser_Id(userId)
            .stream().map(entity ->
                quoteConverter.convertToGeneric(entity.getQuote(), QuoteDto.class))
            .collect(Collectors.toList());
    }

    private List<QuoteDto> searchQuotes(QuoteMultiSearchDto searchDto) {
        return quoteRepository.searchQuotes(searchDto).stream().map(quoteConverter::convertEntityToDto).collect(Collectors.toList());
    }


    @Override
    public Page<QuoteDto> searchQuotePages(QuoteMultiSearchDto searchDto, Pageable pageable) {
        List<QuoteDto> quoteDtos = this.searchQuotes(searchDto);
        return new PageImpl<>(quoteDtos, pageable, quoteDtos.size());
    }

    @Override
    @Transactional
    public Long saveLike(LikeDto likeDto) {
        User user = userService.findById(likeDto.getUserId());
        Quote quote = quoteRepository.findById(likeDto.getQuoteId())
            .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));

        return likeRepository.save(Like.toEntity(user, quote)).getLikeId();
    }
}
