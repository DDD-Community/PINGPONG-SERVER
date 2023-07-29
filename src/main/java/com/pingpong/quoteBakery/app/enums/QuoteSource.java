package com.pingpong.quoteBakery.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuoteSource {
    UNKNOWN("unknown", "미상"),
    GREATMAN("greatman", "위인"),
    CELEB("celeb", "유명인"),
    FILM("film", "드라마/영화"),
    ANIME("anime", "애니메이션"),
    BOOK("book", "책"),
    PROVERB("Proverb", "속담");

    private final String cd;
    private final String nm;
}
