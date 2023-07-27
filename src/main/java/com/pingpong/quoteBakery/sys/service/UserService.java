package com.pingpong.quoteBakery.sys.service;

import com.pingpong.quoteBakery.sys.domain.User;
import com.pingpong.quoteBakery.sys.dto.AddUserRequestDto;
import com.pingpong.quoteBakery.sys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequestDto userReqDto) {
        return userRepository.save(User.builder()
                .email(userReqDto.getEmail())
                .auth(userReqDto.getAuth())
                .password(bCryptPasswordEncoder.encode(userReqDto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

}
