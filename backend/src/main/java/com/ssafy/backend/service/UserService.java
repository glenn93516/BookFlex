package com.ssafy.backend.service;

import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.exception.DuplicatedUsernameException;
import com.ssafy.backend.exception.LoginFailedException;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.mapper.UserMapper;
import com.ssafy.backend.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserDto userDto) {
        if (userMapper.findUserByUserEmail(userDto.getUserEmail()).isPresent() || userMapper.findUserByUserNickname(userDto.getUserNickname()).isPresent()) {
            throw new DuplicatedUsernameException("이미 가입된 유저입니다");
        }

        if (userDto.getUserGender() != null) {
            userDto.setUserGender(userDto.getUserGender().toUpperCase()); // 성별 FEMALE, MALE 대문자
        }

        userDto.setUserPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.save(userDto);

        return userMapper.findUserByUserEmail(userDto.getUsername()).get();
    }

    public String login(LoginDto loginDto) {
        UserDto userDto = userMapper.findUserByUserEmail(loginDto.getUserEmail())
                .orElseThrow(() -> new LoginFailedException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getUserPassword(), userDto.getPassword())) {
            throw new LoginFailedException("잘못된 비밀번호입니다");
        }

        return jwtTokenProvider.createToken(userDto.getUserId(), Collections.singletonList(userDto.getUserRole()));
    }

    public UserDto findByUserId(Long userId) {
        return userMapper.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
    }

    public UserDto findUserByUserNickname(String userNickname) {
        return userMapper.findUserByUserNickname(userNickname)
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
    }

    @Transactional
    public Long updateUser(UserDto findUser, UserDto modifyUser) {
        if (modifyUser.getUserGender() != null) {
            findUser.setUserGender(findUser.getUserGender().toUpperCase()); // 성별 FEMALE, MALE 대문자
        }
        if (modifyUser.getUserBirth() != null) {
            findUser.setUserBirth(modifyUser.getUserBirth());
        }
        if (modifyUser.getUserJob() != null) {
            findUser.setUserJob(modifyUser.getUserJob());
        }
        if (modifyUser.getUserNickname() != null) {
            findUser.setUserNickname(modifyUser.getUserNickname());
        }
        if (modifyUser.getPassword() != null) {
            findUser.setUserPassword(passwordEncoder.encode(findUser.getUserPassword())); // 비밀번호
        }
        if (modifyUser.getUserProfileImg() != null) {
            findUser.setUserProfileImg(modifyUser.getUserProfileImg());
        }

        userMapper.update(findUser);
        return findUser.getUserId();
    }

    @Transactional
    public void deleteOne(Long userId) {
        userMapper.deleteOne(userId);
    }
}
