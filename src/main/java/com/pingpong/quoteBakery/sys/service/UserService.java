package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.com.converter.CommonConverter;
import com.pingpong.quoteBakery.com.exception.BusinessInvalidValueException;
import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.domain.WithdrawalReason;
import com.pingpong.quoteBakery.sys.dto.*;
import com.pingpong.quoteBakery.sys.repository.UserRepository;
import com.pingpong.quoteBakery.sys.repository.WithdrawalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final WithdrawalRepository withdrawalRepository;
    private final CommCdTpService commCdTpService;
    private final CommonConverter commonConverter;
    private final TokenService tokenService;

    @Transactional
    public UserDto saveByFireBase(FBUserRequestDto userReqDto) {
        String randRmk = makeRandomRmk();

        User user = userRepository.save(User.builder()
                .uid(userReqDto.getUid())
                .fcm(userReqDto.getFcm())
                .email(userReqDto.getEmail())
                .nickname(userReqDto.getNickname())
                .jobCd(userReqDto.getJobCd())
                .rmk(randRmk)
                .build());

        return commonConverter.convertToGeneric(user, UserDto.class);
    }

    private String makeRandomRmk() {
        CommCdTpDto tpDto = commCdTpService.getCommCdTpByCd("user_desc");
        List<CommCdDto> cdList = tpDto.getCommCds()
                .stream().filter(commCdDto -> Objects.equals(commCdDto.getUseYn(), Boolean.TRUE))
                .collect(Collectors.toList());

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

    @Transactional
    public void withdrawalAccount(String reason){
        String uid = tokenService.getCurrentTokenInfo().getUid();

        User user = userRepository.findByUid(uid).orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));
        userRepository.delete(user);

        if(reason != null) {
            WithdrawalReason reasonEntity = WithdrawalReason.builder().reason(reason).build();
            withdrawalRepository.save(reasonEntity);
        }
    }
}
