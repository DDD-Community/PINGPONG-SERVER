/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
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
