package com.ssafy.backend.service;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.UserBookDto;
import com.ssafy.backend.mapper.BookMapper;
import com.ssafy.backend.mapper.UserBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookshelfService {

    private final BookMapper bookMapper;
    private final UserBookMapper userBookMapper;

    @Transactional
    public List<BookDto> selectBookList(List<UserBookDto> userBookDtoList) throws Exception {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (UserBookDto userBookDto : userBookDtoList) {
            bookDtoList.add(bookMapper.selectBook(userBookDto.getBookIsbn()));
        }
        return bookDtoList;
    }

    @Transactional
    public List<UserBookDto> selectUserBookList(Long userId) throws Exception {
        return userBookMapper.selectUserBookList(userId);
    }

    @Transactional
    public int insertUserBook(UserBookDto userBookDto) throws Exception {
        return userBookMapper.insertUserBook(userBookDto);
    }

    @Transactional
    public int deleteUserBook(Long userBookId) throws Exception {
        return userBookMapper.deleteUserBook(userBookId);
    }
}
