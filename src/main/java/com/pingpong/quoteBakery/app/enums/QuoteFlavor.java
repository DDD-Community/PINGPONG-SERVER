
package com.pingpong.quoteBakery.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuoteFlavor {
    NUTTY("nutty", "고소한맛"),
    LIGHT("light", "담백한맛"),
    SWEET("sweet", "달콤한맛"),
    SALTY("salty", "짭잘한맛"),
    SPICY("spicy", "매콤한맛");


    private final String cd;
    private final String nm;
}
