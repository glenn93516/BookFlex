package com.ssafy.backend.service;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userMapper.findUserByUserId(Long.valueOf(userId))
                .map(this::addAuthorities)
                .orElseThrow(() -> new UserNotFoundException(userId + "> 찾을 수 없습니다."));
    }

    private UserDto addAuthorities(UserDto userDto) {
        userDto.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(userDto.getUserRole())));

        return userDto;
    }
}
