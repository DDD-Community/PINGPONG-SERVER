package com.pingpong.quoteBakery.sys.service.implementation;

import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.domain.CommCdTp;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.repository.CommCdTpRepository;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import com.pingpong.quoteBakery.sys.service.CommCdTpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommCdTpServiceImpl implements CommCdTpService {

    private final CommCdTpRepository commCdTpRepository;
    private final CommCdConverter converter;

    @Override
    public CommCdTpDto getCommCdTpByCd(String commCdTpCd) {
        return converter.convertEntityToDto(commCdTpRepository.findByCommCdTpCd(commCdTpCd));
    }
}
