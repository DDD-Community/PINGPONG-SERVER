package com.pingpong.quoteBakery.com.resource;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class BaseResource implements Serializable {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime regDttm;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modDttm;
    private String regrId;
    private String regrNm;
    private String modrId;
    private String modrNm;
    private String rmk;
    private String rowStatus;
}
