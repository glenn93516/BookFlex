package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.UserBookDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserBookMapper {
    List<UserBookDto> selectUserBookList(Long userId) throws Exception;
    Optional<UserBookDto> findUserBookByUserIdAndBookIsbn(@Param("userId") Long userId, @Param("bookIsbn") Long bookIsbn);
    int insertUserBook(UserBookDto userBookDto) throws Exception;
    int deleteUserBook(Long userBookId) throws Exception;
}
