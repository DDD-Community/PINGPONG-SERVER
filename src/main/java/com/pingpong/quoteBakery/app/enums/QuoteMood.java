package com.pingpong.quoteBakery.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuoteMood {
    MOTIVATION("motivation", "동기부여"),
    SUPPORT("support", "위로"),
    WISDOM("wisdom", "지혜");

    private final String cd;
    private final String nm;
}
