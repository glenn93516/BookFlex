package com.ssafy.backend.service;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;
import com.ssafy.backend.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper mapper;

    @Override
    public int insertBook(BookDto book) throws Exception {
        return mapper.insertBook(book);
    }

    @Override
    public int updateBook(BookDto book) throws Exception {
        return mapper.updateBook(book);
    }

    @Override
    public int deleteBook(long book_isbn) throws Exception {
        return mapper.deleteBook(book_isbn);
    }

    @Override
    public BookDto selectBook(long book_isbn) throws Exception {
        return mapper.selectBook(book_isbn);
    }

    @Override
    public List<ReviewDto> selectReviewList(long book_isbn) throws Exception {
        return mapper.selectReviewList(book_isbn);
    }

    @Override
    public List<BookDto> selectTitleList(String word) throws Exception {
        return mapper.selectTitleList(word);
    }

    @Override
    public List<BookDto> selectAuthorList(String word) throws Exception {
        return mapper.selectAuthorList(word);
    }

    @Override
    public List<BookDto> selectContentsList(String word) throws Exception {
        return mapper.selectContentsList(word);
    }

    @Override
    public List<BookDto> findAll() {
        return mapper.findAll();
    }
}
