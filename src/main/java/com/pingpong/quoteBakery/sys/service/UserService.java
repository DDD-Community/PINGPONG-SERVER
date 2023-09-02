package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.dto.CommCdDto;
import com.pingpong.quoteBakery.sys.dto.CommCdTpDto;
import com.pingpong.quoteBakery.sys.dto.FBUserRequestDto;
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

    public String saveByFireBase(FBUserRequestDto userReqDto) {
        String randRmk = makeRandomRmk();

        return userRepository.save(User.builder()
                .uid(userReqDto.getUid())
                .fcm(userReqDto.getFcm())
                .email(userReqDto.getEmail())
                .nickName(userReqDto.getNickName())
                .jobCd(userReqDto.getJobCd())
                .rmk(randRmk)
                .build()).getUid();
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
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public boolean validateNickName(String nickName){
        return userRepository.existsByNickName(nickName);
    }

}
