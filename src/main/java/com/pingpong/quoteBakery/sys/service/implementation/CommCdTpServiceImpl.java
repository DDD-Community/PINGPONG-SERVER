package com.pingpong.quoteBakery.sys.service.implementation;

import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
import com.pingpong.quoteBakery.sys.domain.CommCdTp;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.repository.CommCdTpRepository;
import com.pingpong.quoteBakery.sys.resource.CommCdConverter;
import com.pingpong.quoteBakery.sys.service.CommCdTpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<CommCdTpDto> getCommCdTpListByCd(List<String> commCdTpCds) {
        return commCdTpRepository.findAllByCommCdTpCdIn(commCdTpCds).stream().map(converter::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CommCdTpDto getCommCdTpById(Long commCdTpId) {
        CommCdTp commCdTp = commCdTpRepository.findById(commCdTpId)
                .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));

        return converter.convertEntityToDto(commCdTp);
    }
}
