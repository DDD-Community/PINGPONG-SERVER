package com.pingpong.quoteBakery.app.service.implementation;

import com.pingpong.quoteBakery.app.domain.UserPreference;
import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.persistence.UserPreferenceRepository;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.service.CommCdTpService;
import com.pingpong.quoteBakery.sys.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserPrefServiceImpl implements UserPrefService {

    private final CommCdTpService commCdTpService;
    private final UserService userService;
    private final UserPreferenceRepository userPreferenceRepository;
    private final CommonConverter commonConverter;

    public static final String FLAVOR = "flavor";
    public static final String SOURCE = "source";

    @Override
    public List<CommCdTpDto> searchUserPrefCode() {
        return commCdTpService.getCommCdTpListByCd(new ArrayList<>(Arrays.asList(FLAVOR, SOURCE)));
    }

    @Override
    @Transactional
    public Long saveUserPref(UserPrefDto userPrefDto) {
        User user = userService.findById(userPrefDto.getUserId());
        return userPreferenceRepository.save(UserPreference.toEntity(userPrefDto, user)).getUserPrefId();
    }

    @Override
    public UserPrefDto getUserPrefByUserId(Long userId) {
        UserPrefDto userPrefDto = commonConverter.convertToGeneric(userPreferenceRepository.findByUser_Id(userId), UserPrefDto.class);
        userPrefDto.setUserId(userId);

        return userPrefDto;
    }
}
