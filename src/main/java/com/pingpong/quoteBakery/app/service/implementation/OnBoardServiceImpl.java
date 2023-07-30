package com.pingpong.quoteBakery.app.service.implementation;

import com.pingpong.quoteBakery.app.service.OnBoardService;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.service.CommCdTpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OnBoardServiceImpl implements OnBoardService {

    private final CommCdTpService commCdTpService;

    public static final String FLAVOR = "flavor";
    public static final String SOURCE = "source";

    @Override
    public List<CommCdTpDto> searchUserPrefCode() {
        return commCdTpService.getCommCdTpListByCd(new ArrayList<>(Arrays.asList(FLAVOR, SOURCE)));
    }
}
