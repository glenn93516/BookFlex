package com.ssafy.backend.controller;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;
import com.ssafy.backend.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    //책 등록
    @PostMapping(value = "")
    private ResponseEntity createBook(@RequestBody BookDto book) {
        ResponseEntity entity = null;
        Map result = new HashMap();
        try {
            if(bookService.insertBook(book) == 1) {
                result.put("success", true);
                result.put("message","도서 등록 성공");
                entity = new ResponseEntity(result, HttpStatus.CREATED);
            }
        } catch (DataIntegrityViolationException e){
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message","Duplicate");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
//            e.printStackTrace();
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message","error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    //책 정보 조회
    @GetMapping(value = "/{book_isbn}")
    private ResponseEntity detailBook(@PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity entity = null;
        Map result = new HashMap();
        BookDto book = null;
        try {
            book = bookService.selectBook(book_isbn);
            if(book != null){
                result.put("success", true);
                result.put("message","도서 조회 성공");
                result.put("data", book);
                entity = new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("success", false);
                result.put("message","일치 도서 없음");
                entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message","error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    //책 정보 수정
    @PutMapping(value = "/{book_isbn}")
    private ResponseEntity updateBook(@RequestBody BookDto book, @PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity entity = null;
        Map result = new HashMap();
        book.setBook_isbn(book_isbn);
        try {
            if (bookService.updateBook(book) == 1) {
                result.put("success", true);
                result.put("message","도서 수정 성공");
                entity = new ResponseEntity<>(result, HttpStatus.OK);
            }else {
                result.put("success", false);
                result.put("message","일치 도서 없음");
                entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    //책 정보 삭제
    @DeleteMapping(value = "/{book_isbn}")
    private ResponseEntity deleteBook(@PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity entity = null;
        Map result = new HashMap();
        try {
            if (bookService.deleteBook(book_isbn)== 1) {
                result.put("success", true);
                entity = new ResponseEntity<>(result, HttpStatus.OK);
            }
            else {
                result.put("success", false);
                result.put("message","일치 도서 없음");
                entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    //책 리뷰 조회
    @GetMapping(value = "/{book_isbn}/review")
    private ResponseEntity getReviewList(@PathVariable(name = "book_isbn") long book_isbn){
        ResponseEntity entity = null;
        Map result = new HashMap();
        List<ReviewDto> list = null;
        try {
            list = bookService.selectReviewList(book_isbn);
            result.put("success", "success");
            result.put("data", list);
            entity = new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    //책 검색
    @GetMapping(value = "")
    private ResponseEntity getSearchList(@RequestParam(defaultValue="title") String search, @RequestParam(defaultValue="") String word){
        ResponseEntity entity = null;
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
            result.put("success", "success");
            result.put("data", list);
            entity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.debug(e.getMessage());
            result.put("success", false);
            result.put("message", "error");
            entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
