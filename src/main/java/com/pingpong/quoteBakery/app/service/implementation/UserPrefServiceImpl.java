package com.pingpong.quoteBakery.app.service.implementation;

import static com.pingpong.quoteBakery.com.util.StringUtil.convertListToString;
import static com.pingpong.quoteBakery.com.util.StringUtil.convertStringToList;

import com.pingpong.quoteBakery.app.domain.UserPreference;
import com.pingpong.quoteBakery.app.dto.UserPrefDto;
import com.pingpong.quoteBakery.app.persistence.UserPreferenceRepository;
import com.pingpong.quoteBakery.app.service.UserPrefService;
import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
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
    public static final String JOB = "job";

    @Override
    public List<CommCdTpDto> searchUserPrefCode() {
        return commCdTpService.getCommCdTpListByCd(new ArrayList<>(Arrays.asList(FLAVOR, SOURCE, JOB)));
    }

    @Override
    @Transactional
    public Long createUserPref(UserPrefDto userPrefDto) {
        userPrefDto.setFlavor(convertListToString(",", userPrefDto.getFlavors()));
        userPrefDto.setSource(convertListToString(",", userPrefDto.getSources()));

        User user = userService.findById(userPrefDto.getUserId());

        // 이미 등록된 유저인지 확인
        if(userPreferenceRepository.findByUser_Id(user.getId()) != null){
            throw new BusinessInvalidValueException("이미 취향이 등록된 유저입니다.");
        }
        return userPreferenceRepository.save(UserPreference.toEntity(userPrefDto, user)).getUserPrefId();
    }

    @Override
    @Transactional
    public Long updateUserPref(UserPrefDto userPrefDto) {
        UserPreference userPreference = userPreferenceRepository.findById(userPrefDto.getUserPrefId())
            .orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));

        // convert List to String for storing in DB
        userPrefDto.setFlavor(convertListToString(",", userPrefDto.getFlavors()));
        userPrefDto.setSource(convertListToString(",", userPrefDto.getSources()));

        userPreference.update(userPrefDto);

        return userPreference.getUserPrefId();
    }

    @Override
    public UserPrefDto getUserPrefByUserId(Long userId) {
        UserPrefDto userPrefDto = commonConverter.convertToGeneric(userPreferenceRepository.findByUser_Id(userId), UserPrefDto.class);
        // "nutty,spicy" -> ['nutty', 'spicy']
        userPrefDto.setFlavors(convertStringToList(",", userPrefDto.getFlavor()));
        userPrefDto.setSources(convertStringToList(",", userPrefDto.getSource()));
        userPrefDto.setUserId(userId);

        return userPrefDto;
    }
}
