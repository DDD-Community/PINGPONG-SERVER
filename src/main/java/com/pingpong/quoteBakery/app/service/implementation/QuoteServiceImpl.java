package com.pingpong.quoteBakery.app.service.implementation;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.domain.Scrap;
import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.QuoteSingleSearchDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.persistence.LikeRepository;
import com.pingpong.quoteBakery.app.persistence.QuoteRepository;
import com.pingpong.quoteBakery.app.persistence.ScrapRepository;
import com.pingpong.quoteBakery.app.resource.QuoteConverter;
import com.pingpong.quoteBakery.app.service.QuoteService;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final UserPrefService userPrefService;
    private final UserService userService;
    private final QuoteRepository quoteRepository;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;
    private final QuoteConverter quoteConverter;

    @Override
    public QuoteDto getRandomQuoteByUser(Long userId) {
        UserPrefDto userPrefDto = userPrefService.getUserPrefByUserId(userId);
        QuoteMultiSearchDto searchDto = quoteConverter.convertToGeneric(userPrefDto, QuoteMultiSearchDto.class);

        return this.getRandomQuoteWithMulti(searchDto);
    }

    @Override
    public QuoteDto getRandomQuoteWithMulti(QuoteMultiSearchDto searchDto) {
        QuoteDto quoteDto = quoteConverter.convertEntityToDto(quoteRepository.searchQuoteWithMulti(searchDto));

        // resource converter에서 좋아요/보관여부 세팅시에 필요
        if(quoteDto != null) quoteDto.setUserId(searchDto.getUserId());
        return quoteDto;
    }


    @Override
    public QuoteDto getRandomQuoteWithSingle(QuoteSingleSearchDto searchDto) {
        QuoteDto quoteDto = quoteConverter.convertEntityToDto(quoteRepository.searchQuoteWithSingle(searchDto));

        // resource converter에서 좋아요/보관여부 세팅시에 필요
        if(quoteDto != null) quoteDto.setUserId(searchDto.getUserId());
        return quoteDto;
    }

    @Override
    public List<QuoteDto> getLikedQuotes(Long userId) {

        return likeRepository.findAllByUser_Id(userId)
            .stream().map(entity ->
                quoteConverter.convertToGeneric(entity.getQuote(), QuoteDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<QuoteDto> getScrapedQuotes(Long userId) {
        return scrapRepository.findAllByUser_Id(userId)
            .stream().map(entity ->
                quoteConverter.convertToGeneric(entity.getQuote(), QuoteDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<QuoteDto> searchQuotes(QuoteMultiSearchDto searchDto) {
        return quoteRepository.searchQutes(searchDto).stream().map(quoteConverter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long saveLike(LikeDto likeDto) {
        User user = userService.findById(likeDto.getUserId());
        Quote quote = quoteRepository.findById(likeDto.getQuoteId())
            .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));

        return likeRepository.save(Like.toEntity(user, quote)).getLikeId();
    }

    @Override
    @Transactional
    public Long saveScrap(ScrapDto scrapDto) {
        User user = userService.findById(scrapDto.getUserId());
        Quote quote = quoteRepository.findById(scrapDto.getQuoteId())
            .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));

        return scrapRepository.save(Scrap.toEntity(user, quote)).getScrapId();
    }


}
