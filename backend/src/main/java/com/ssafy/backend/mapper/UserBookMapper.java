package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.UserBookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBookMapper {
    List<UserBookDto> selectUserBookList(Long userId) throws Exception;

    int insertUserBook(UserBookDto userBookDto) throws Exception;
    int deleteUserBook(Long userBookId) throws Exception;
}
