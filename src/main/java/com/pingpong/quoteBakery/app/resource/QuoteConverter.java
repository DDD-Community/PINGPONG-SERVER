package com.pingpong.quoteBakery.app.resource;

import com.pingpong.quoteBakery.app.domain.Like;
import com.pingpong.quoteBakery.app.domain.Quote;
import com.pingpong.quoteBakery.app.dto.LikeDto;
import com.pingpong.quoteBakery.app.dto.QuoteDto;
import com.pingpong.quoteBakery.app.dto.QuoteMultiSearchDto;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class QuoteConverter extends CommonConverter {
    public QuoteResource convertDtoToRandomResource(QuoteDto quoteDto, Long userId){
        if(quoteDto == null) return null;

        QuoteResource resource  = convertToGeneric(quoteDto, QuoteResource.class);

        // 요청한 사용자가 좋아요했는지 여부를 판단하여 세팅
        List<LikeDto> likes = quoteDto.getLikes();
        if(likes != null){
            resource.setLikeId(
                    likes.stream().filter(likeDto -> likeDto.getUserId().equals(userId))
                            .findAny().orElseGet(LikeDto::new).getLikeId()
            );
        }
        return resource;
    }

    public QuoteDto convertEntityToDto(Quote quote){
        if(quote == null) return null;

        QuoteDto quoteDto = convertToGeneric(quote, QuoteDto.class);

        List<Like> likes = quote.getLikes();
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
