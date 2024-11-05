package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserMapper;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        log.info("회원가입 요청 : {}", userCreateRequest);
//        중복 검사
        if (null != userRepository.findByName(userCreateRequest.getName())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
//        사용자 생성
        User user = userMapper.toUserEntity(userCreateRequest);
//        사용자 저장
        userRepository.save(user);
        return userMapper.toUserDTO(user);
        }
}
