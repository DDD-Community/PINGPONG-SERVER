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
import com.pingpong.quoteBakery.sys.service.TokenService;
import com.pingpong.quoteBakery.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final TokenService tokenService;

    @Override
    public Page<QuoteDto> searchRandomQuotesByUser(Long userId, Pageable pageable) {
        QuoteMultiSearchDto searchDto = new QuoteMultiSearchDto();
        if (userId != null) {
            UserPrefDto userPrefDto = userPrefService.getUserPrefByUserId(userId);
            searchDto = quoteConverter.convertToGeneric(userPrefDto, QuoteMultiSearchDto.class);
        }
        return this.searchQuotes(searchDto, pageable);
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
                .stream().map(entity -> {
                    QuoteDto dto = quoteConverter.convertToGeneric(entity.getQuote(), QuoteDto.class);
                    dto.setLikeId(entity.getLikeId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private Page<QuoteDto> searchQuotes(QuoteMultiSearchDto searchDto, Pageable pageable) {
        return quoteRepository.searchQuotes(searchDto, pageable).map(quoteConverter::convertEntityToDto);
    }


    @Override
    public Page<QuoteDto> searchQuotePages(QuoteMultiSearchDto searchDto, Pageable pageable) {
        return this.searchQuotes(searchDto, pageable);
    }

    @Override
    @Transactional
    public Long saveLike(LikeDto likeDto) {
        User user = userService.findById(likeDto.getUserId());
        Quote quote = quoteRepository.findById(likeDto.getQuoteId())
                .orElseThrow(() -> new BusinessInvalidValueException("해당 명언에 대한 정보가 없습니다."));

        boolean existYn = likeRepository.existsByUserAndQuote(user, quote);
        if (existYn) throw new BusinessInvalidValueException("이미 좋아요 등록된 명언입니다.");

        return likeRepository.save(Like.toEntity(user, quote)).getLikeId();
    }

    @Override
    @Transactional
    public void deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId)
                .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 좋아요 정보가 없습니다."));

        String tokenUid = tokenService.getCurrentTokenInfo().getUid();
        String requestUid = like.getUser().getUid();

        if (!requestUid.equals(tokenUid)) throw new BusinessInvalidValueException("본인만 좋아요 취소할 수 있습니다.");

        likeRepository.delete(like);
    }
}
