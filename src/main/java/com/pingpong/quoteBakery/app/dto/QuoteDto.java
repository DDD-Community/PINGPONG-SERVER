package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuoteDto extends BaseDto {
    private Long quoteId;

    private String content;

    private String author;

    private String flavor;

    private String source;

    private String mood;

    private List<LikeDto> likes = new ArrayList<>();

    // quoteConverter likes 변환용
    private Long userId;
}

