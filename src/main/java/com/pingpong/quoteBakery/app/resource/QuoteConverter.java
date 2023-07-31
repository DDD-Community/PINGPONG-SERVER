package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.domain.Scrap;
import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.ScrapDto;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
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
        if(likes != null){ quoteDto.setLikes(likes.stream().map(entity -> this.convertToGeneric(entity, LikeDto.class)).collect(Collectors.toList())); }
        if(scraps != null){ quoteDto.setScraps(scraps.stream().map(entity -> this.convertToGeneric(entity, ScrapDto.class)).collect(Collectors.toList())); }

        return quoteDto;
    }
}
