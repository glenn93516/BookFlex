package com.ssafy.backend.controller;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.BookService;
import com.ssafy.backend.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private final BookService bookService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    //도서 등록
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity createBook(@RequestBody BookDto book) {
        ResponseEntity responseEntity = null;
        try {
            if(bookService.insertBook(book) == 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 등록 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
        }
//        catch (DataIntegrityViolationException exception){
//            logger.debug(exception.getMessage());
//            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
//            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
        catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //책 정보 조회
    @GetMapping(value = "/{book_isbn}")
    public ResponseEntity detailBook(@PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        BookDto book = null;
        try {
            book = bookService.selectBook(book_isbn);
            if(book != null){
                SingleDataResponse<BookDto> response = responseService.getSingleDataResponse(true, "도서 상세 조회 성공", book);
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
            else{
                SingleDataResponse<BookDto> response = responseService.getSingleDataResponse(false, "일치 도서 없음", null);
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //책 정보 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{book_isbn}")
    public ResponseEntity updateBook(@RequestBody BookDto book, @PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        book.setBook_isbn(book_isbn);
        try {
            if (bookService.updateBook(book) == 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 수정 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
            else {
                BaseResponse response = responseService.getBaseResponse(false, "일치 도서 없음");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //책 정보 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{book_isbn}")
    public ResponseEntity deleteBook(@PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        try {
            if (bookService.deleteBook(book_isbn)== 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 삭제 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
            else {
                BaseResponse response = responseService.getBaseResponse(false, "일치 도서 없음");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //책 리뷰 조회
    @GetMapping(value = "/{book_isbn}/review")
    public ResponseEntity getReviewList(@PathVariable(name = "book_isbn") long book_isbn){
        ResponseEntity responseEntity = null;
        List<ReviewDto> list = null;
        try {
            list = bookService.selectReviewList(book_isbn);
            ListDataResponse<ReviewDto> response = responseService.getListDataResponse(true, "도서 리뷰 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //책 검색
    @GetMapping(value = "")
    public ResponseEntity getSearchList(@RequestParam(defaultValue="title") String search, @RequestParam(defaultValue="") String word){
        ResponseEntity responseEntity = null;
        Map result = new HashMap();
        List<BookDto> list = null;
        try {
            if(search.equals("title")){
                list = bookService.selectTitleList(word);
            }else if(search.equals("author")){
                list = bookService.selectAuthorList(word);
            }else if(search.equals("contents")) {
                list = bookService.selectContentsList(word);
            }
            ListDataResponse<BookDto> response = responseService.getListDataResponse(true, "도서 검색 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
