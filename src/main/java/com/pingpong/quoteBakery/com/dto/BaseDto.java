package com.pingpong.quoteBakery.com.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto implements Serializable {
    private String rmk;
    private String regrId;
    private LocalDateTime regDttm;
    private String modrId;
    private LocalDateTime modDttm;
}