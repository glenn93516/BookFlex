package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findUserByUserEmail(String userEmail);
    Optional<UserDto> findUserByUserId(Long userId);
    void save(UserDto userDto);
}
