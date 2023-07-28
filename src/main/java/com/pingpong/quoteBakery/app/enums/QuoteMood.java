/**
 * Copyright ⓒ Kolon Benit CO. LTD. All rights reserved.
 * 코오롱베니트(주)의 사전 승인 없이 본 내용의 전부 또는 일부에 대한 복사, 배포, 사용을 금합니다.
 */
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
