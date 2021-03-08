package com.ssafy.backend.service;

import com.ssafy.backend.dto.BookDto;

public interface BookService {
    int insertBook(BookDto book) throws Exception;
    int updateBook(BookDto book) throws Exception;
    int deleteBook(long book_isbn) throws Exception;

    BookDto selectBook(long book_isbn) throws Exception;
}
