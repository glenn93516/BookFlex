package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    int insertBook(BookDto book) throws Exception;
    int updateBook(BookDto book) throws Exception;
    int deleteBook(long book_isbn) throws Exception;

    BookDto selectBook(long book_isbn) throws Exception;
    List<ReviewDto> selectReviewList(long book_isbn) throws Exception;
}
