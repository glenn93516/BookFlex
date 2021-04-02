package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.GenreDto;
import com.ssafy.backend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findUserByUserEmail(String userEmail);
    Optional<UserDto> findUserByUserId(Long userId);
    void save(UserDto userDto);
    void saveUserGenres(@Param("userId") Long userId, @Param("genres") List<GenreDto> genres);
    void update(UserDto userDto);
    Optional<UserDto> findUserByUserNickname(String userNickname);
    void deleteOne(Long userId);
    void saveNewPassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);
}
