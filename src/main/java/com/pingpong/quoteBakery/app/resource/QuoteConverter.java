package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.domain.Scrap;
import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QuoteConverter extends CommonConverter {
    // RandomQuoteResource는 항상 사용자별로 요청됨.
    public RandomQuoteResource convertDtoToRandomResource(QuoteDto quoteDto){
        if(quoteDto == null) return null;

        RandomQuoteResource resource  = convertToGeneric(quoteDto, RandomQuoteResource.class);

        // 요청한 사용자가 좋아요/보관을 했는지 여부를 판단하여 세팅
        List<LikeDto> likes = quoteDto.getLikes();
        List<ScrapDto> scraps = quoteDto.getScraps();
        if(likes != null){
           long cnt = likes.stream().filter(likeDto -> likeDto.getUserId().equals(quoteDto.getUserId())).count();
           if(cnt > 0) { resource.setLikeYn(Boolean.TRUE); } else { resource.setLikeYn(Boolean.FALSE); }
        }
        if(scraps != null){
            long cnt = scraps.stream().filter(scrapDto -> scrapDto.getUserId().equals(quoteDto.getUserId())).count();
            if(cnt > 0) { resource.setScrapYn(Boolean.TRUE); } else { resource.setScrapYn(Boolean.FALSE); }
        }

        return resource;
    }

    public QuoteDto convertEntityToDto(Quote quote){
        if(quote == null) return null;

        QuoteDto quoteDto = convertToGeneric(quote, QuoteDto.class);

        List<Like> likes = quote.getLikes();
        List<Scrap> scraps = quote.getScraps();
        if(likes != null){
            quoteDto.setLikes(likes.stream().map(entity -> {
                LikeDto likeDto = new LikeDto();
                likeDto.setLikeId(entity.getLikeId());
                likeDto.setUserId(entity.getUser().getId());
                likeDto.setQuoteId(entity.getQuote().getQuoteId());
                return likeDto;
            })
            .collect(Collectors.toList()));
        }
        if(scraps != null){
            quoteDto.setScraps(scraps.stream().map(entity -> {
                ScrapDto scrapDto = new ScrapDto();
                scrapDto.setScrapId(entity.getScrapId());
                scrapDto.setUserId(entity.getUser().getId());
                scrapDto.setQuoteId(entity.getQuote().getQuoteId());
                return scrapDto;
            })
            .collect(Collectors.toList()));
        }

        return quoteDto;
    }

    public QuoteMultiSearchDto convertSearchResourceToDto(QuoteSearchResource searchResource){
        if(searchResource == null) return null;
        QuoteMultiSearchDto dto  = convertToGeneric(searchResource, QuoteMultiSearchDto.class);

        // Deep copy the List
        if(searchResource.getFlavors() != null){
            dto.setFlavors(new ArrayList<>(searchResource.getFlavors()));
        }
        if(searchResource.getSources() != null){
            dto.setSources(new ArrayList<>(searchResource.getSources()));
        }
        if(searchResource.getMoods() != null){
            dto.setMoods(new ArrayList<>(searchResource.getMoods()));
        }

        return dto;
    }

}
