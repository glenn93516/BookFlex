package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    int insertBook(BookDto book) throws Exception;
    int updateBook(BookDto book) throws Exception;
    int deleteBook(long book_isbn) throws Exception;

    BookDto selectBook(long book_isbn) throws Exception;
}
