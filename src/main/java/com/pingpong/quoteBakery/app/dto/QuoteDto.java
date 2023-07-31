package com.pingpong.quoteBakery.app.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
public class QuoteDto {
    private Long quoteId;

    private String content;

    private String author;

    private String flavor;

    private String source;

    private String mood;

    private List<LikeDto> likes = new ArrayList<>();

    private List<ScrapDto> scraps = new ArrayList<>();

    // search
    private String keyword;
    private Long userId;
}

