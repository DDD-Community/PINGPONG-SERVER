package com.pingpong.quoteBakery.sys.resource;

import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.domain.CommCd;
import com.pingpong.quoteBakery.sys.domain.CommCdTp;
import com.pingpong.quoteBakery.sys.dto.CommCdDto;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CommCdConverter extends CommonConverter {

    public CommCdTpResource convertDtoToResource(CommCdTpDto commCdTpDto){
        if(commCdTpDto == null) return null;

        CommCdTpResource resource  = convertToGeneric(commCdTpDto, CommCdTpResource.class);
        if(commCdTpDto.getCommCds() != null){
            resource.setCommCds(commCdTpDto.getCommCds().stream().map(dto -> this.convertToGeneric(dto, CommCdResource.class)).collect(Collectors.toSet()));
        }

        return resource;
    }

    public CommCdTpDto convertEntityToDto(CommCdTp commCdTp){
        CommCdTpDto commCdTpDto = convertToGeneric(commCdTp, CommCdTpDto.class);

        Set<CommCd> temp = commCdTp.getCommCds();
        if(commCdTp.getCommCds() != null){
            commCdTpDto.setCommCds(commCdTp.getCommCds().stream().map(entity -> this.convertToGeneric(entity, CommCdDto.class)).collect(Collectors.toSet()));
        }
        return commCdTpDto;
    }
}
