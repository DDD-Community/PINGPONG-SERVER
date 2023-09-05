package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.app.persistence.LikeRepository;
import com.pingpong.quoteBakery.app.persistence.ScrapRepository;
import com.pingpong.quoteBakery.app.persistence.UserPreferenceRepository;
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
    private final UserPreferenceRepository userPreferenceRepository;
    private final LikeRepository likeRepository;
    private final ScrapRepository scrapRepository;

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
                .toList();

        if (!cdList.isEmpty()) {
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
    public void withdrawalAccount(WithdrawalDto dto){
        String tokenUid = tokenService.getCurrentTokenInfo().getUid();
        String requestUid = dto.getUid();

        if(!requestUid.equals(tokenUid)) throw new BusinessInvalidValueException("본인만 회원 탈퇴할 수 있습니다.");

        User user = userRepository.findByUid(requestUid).orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));
        userPreferenceRepository.deleteAllByUserId(user.getId());
        likeRepository.deleteAllByUserId(user.getId());
        scrapRepository.deleteAllByUserId(user.getId());
        userRepository.delete(user);

        if(dto.getReason() != null) {
            WithdrawalReason reasonEntity = WithdrawalReason.builder().reason(dto.getReason()).build();
            withdrawalRepository.save(reasonEntity);
        }
    }

    @Transactional
    public void updateUserInfo(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new BusinessInvalidValueException("해당 ID에 대한 정보가 없습니다."));
        String tokenUid = tokenService.getCurrentTokenInfo().getUid();

        if(tokenUid == null) throw new BusinessInvalidValueException("로그인해주세요.");

        if(!tokenUid.equals(user.getUid())) throw new BusinessInvalidValueException("본인만 수정할 수 있습니다.");

        user.updateUserInfo(userDto);
    }
}
