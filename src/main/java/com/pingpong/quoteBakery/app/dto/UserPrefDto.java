package com.pingpong.quoteBakery.app.dto;

import com.pingpong.quoteBakery.com.dto.BaseDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPrefDto extends BaseDto {
    private Long userPrefId;

    private String flavor;

    private String source;

    private Long userId;

    private List<String> flavors;

    private List<String> sources;
}

