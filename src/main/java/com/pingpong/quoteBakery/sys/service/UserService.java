package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.dto.CommCdDto;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.dto.FBUserRequestDto;
import com.pingpong.quoteBakery.sys.dto.UserDto;
import com.pingpong.quoteBakery.sys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CommCdTpService commCdTpService;
    private final CommonConverter commonConverter;

    public Long saveByFireBase(FBUserRequestDto userReqDto) {
        String randRmk = makeRandomRmk();

        return userRepository.save(User.builder()
                .uid(userReqDto.getUid())
                .fcm(userReqDto.getFcm())
                .email(userReqDto.getEmail())
                .nickname(userReqDto.getNickname())
                .jobCd(userReqDto.getJobCd())
                .rmk(randRmk)
                .build()).getId();
    }

    private String makeRandomRmk() {
        CommCdTpDto dto = commCdTpService.getCommCdTpByCd("user_desc");
        List<CommCdDto> cdList = dto.getCommCds();

        if (cdList != null && !cdList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(cdList.size());
            CommCdDto randomCommCd = cdList.get(randomIndex);

            return randomCommCd.getCommNm();
        }

        return "";
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessInvalidValueException("Unexpected user"));
    }

    public UserDto findByUid(String uid){
        User user = userRepository.findByUid(uid).orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));
        return commonConverter.convertToGeneric(user, UserDto.class);
    }

    public boolean validateNickname(String nickname){
        return !userRepository.existsByNickname(nickname);
    }

    public boolean validateUid(String uid){
        return userRepository.existsByUid(uid);
    }
}
