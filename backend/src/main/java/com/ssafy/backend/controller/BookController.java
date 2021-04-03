package com.ssafy.backend.controller;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.ReviewDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.BookService;
import com.ssafy.backend.service.ResponseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final String BASE_FLASK_URL = "https://j4f004.p.ssafy.io/ml/api";
//    private final String BASE_FLASK_URL = "http://localhost:5000/ml/api";
    private final BookService bookService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    // 도서 등록
    @ApiOperation(value = "도서 등록", notes = "더미데이터 삽입용도. 관리자 계정으로만 사용 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "")
    public ResponseEntity createBook(@ApiParam(value = "등록할 책 정보", required = true) @RequestBody BookDto book,
                                     @ApiIgnore final Authentication authentication) {
        // public ResponseEntity createBook(@RequestBody BookDto book) {
        ResponseEntity responseEntity = null;
        try {
            if (bookService.insertBook(book) == 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 등록 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
        }
        // catch (DataIntegrityViolationException exception){
        // logger.debug(exception.getMessage());
        // BaseResponse response = responseService.getBaseResponse(false,
        // exception.getMessage());
        // responseEntity =
        // ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        // }
        catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 정보 조회
    @ApiOperation(value = "도서 상세 정보 조회")
    @GetMapping(value = "/{book_isbn}")
    public ResponseEntity detailBook(@ApiParam(value = "조회할 책 isbn", required = true, example = "8954672213") @PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        BookDto book = null;
        try {
            book = bookService.selectBook(book_isbn);
            if (book != null) {
                SingleDataResponse<BookDto> response = responseService.getSingleDataResponse(true, "도서 상세 조회 성공", book);
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            } else {
                SingleDataResponse<BookDto> response = responseService.getSingleDataResponse(false, "일치 도서 없음", null);
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 정보 수정
    @ApiOperation(value = "도서 정보 수정", notes = "더미 데이터 수정용. 관리자 계정으로만 사용 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{book_isbn}")
    public ResponseEntity updateBook(@ApiParam(value = "수정한 책 정보", required = true) @RequestBody BookDto book,
                                     @ApiParam(value = "조회할 책 isbn", required = true, example = "8954672213") @PathVariable(name = "book_isbn") long book_isbn,
                                     @ApiIgnore final Authentication authentication) {
        // public ResponseEntity updateBook(@RequestBody BookDto book,
        // @PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        book.setBook_isbn(book_isbn);
        try {
            if (bookService.updateBook(book) == 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 수정 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            } else {
                BaseResponse response = responseService.getBaseResponse(false, "일치 도서 없음");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 정보 삭제
    @ApiOperation(value = "도서 정보 삭제", notes = "더미 데이터 삭제용. 관리자 계정으로만 사용 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{book_isbn}")
    public ResponseEntity deleteBook(@ApiParam(value = "삭제할 책 isbn", required = true, example = "8954672213") @PathVariable(name = "book_isbn") long book_isbn,
                                     @ApiIgnore final Authentication authentication) {
        // public ResponseEntity deleteBook(@PathVariable(name = "book_isbn") long
        // book_isbn) {
        ResponseEntity responseEntity = null;
        try {
            if (bookService.deleteBook(book_isbn) == 1) {
                BaseResponse response = responseService.getBaseResponse(true, "도서 삭제 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            } else {
                BaseResponse response = responseService.getBaseResponse(false, "일치 도서 없음");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 리뷰 조회
    @ApiOperation(value = "도서 리뷰 목록 조회")
    @GetMapping(value = "/{book_isbn}/review")
    public ResponseEntity getReviewList(@ApiParam(value = "리뷰 조회할 책 isbn", required = true, example = "8954672213") @PathVariable(name = "book_isbn") long book_isbn) {
        ResponseEntity responseEntity = null;
        List<ReviewDto> list = null;
        try {
            list = bookService.selectReviewList(book_isbn);
            ListDataResponse<ReviewDto> response = responseService.getListDataResponse(true, "도서 리뷰 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 검색
    @ApiOperation(value = "도서 검색", notes = "검색 타입 없을 시 전체 책 목록에서 20개 반환")
    @GetMapping(value = "")
    public ResponseEntity getSearchList(@ApiParam(value = "검색 타입", required = false) @RequestParam(defaultValue = "") String search,
                                        @ApiParam(value = "검색어", required = false) @RequestParam(defaultValue = "") String word) {
        ResponseEntity responseEntity = null;
        Map result = new HashMap();
        List<BookDto> list = null;
        try {
            if (search.equals("")) {
                list = bookService.findAll();
            } else if (search.equals("title")) {
                list = bookService.selectTitleList(word);
            } else if (search.equals("author")) {
                list = bookService.selectAuthorList(word);
            } else if (search.equals("contents")) {
                list = bookService.selectContentsList(word);
            }
            ListDataResponse<BookDto> response = responseService.getListDataResponse(true, "도서 검색 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 관련 도서 조회
    @ApiOperation(value = "현재 책이랑 비슷한 추천 도서 조회", notes = "현재 책이랑 유사한 책 20개 추천")
    @GetMapping(value = "/{book_isbn}/recommend")
    public ResponseEntity getSimilarBooks(@ApiParam(value = "선택한 책 isbn", required = true, example = "9791136202772") @PathVariable(name = "book_isbn") Long book_isbn) {
        String url = BASE_FLASK_URL + "/recommend/book/" + book_isbn;
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
    }

    // 도서 감정 분석
    @ApiOperation(value = "책의 감정 분석 데이터 조회", notes = "현재 책 리뷰를 감성분석한 결과 전달")
    @GetMapping(value = "/{book_isbn}/sentiment")
    public ResponseEntity getBookSentiment(@ApiParam(value = "선택한 책 isbn", required = true, example = "9791136202772") @PathVariable(name = "book_isbn") Long book_isbn) {
        String url = BASE_FLASK_URL + "/book/" + book_isbn + "/sentiment";
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
    }

    // 도서 워드클라우드 조회
    @ApiOperation(value = "현재 책 워드클라우드 데이터 조회", notes = "리뷰의 키워드와 키워드 개수 데이터 전달")
    @GetMapping(value = "/{book_isbn}/wordcloud")
    public ResponseEntity getBookWordCloud(@ApiParam(value = "조회할 책 isbn", required = true, example = "9791136202772") @PathVariable(name = "book_isbn") Long book_isbn) {
        ResponseEntity responseEntity = null;
        String url = BASE_FLASK_URL + "/book/" + book_isbn + "/wordcloud";
        try {
            BookDto bookDto = bookService.selectBook(book_isbn);
            if (bookDto == null) {
                throw new IllegalStateException("잘못된 책 ISBN 입니다");
            }
            responseEntity =  ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
        } catch (Exception exception) {
            // 잘못된 책 ISBN인 경우
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    // 도서 토픽 분석 조회
    @ApiOperation(value = "현재 책 토픽 분석 데이터 조회", notes = "책의 토픽 분류와 해당 토픽에 속하는 키워드 조회")
    @GetMapping(value = "/{book_isbn}/topics")
    public ResponseEntity getBookTopics(@ApiParam(value = "조회할 책 isbn", required = true, example = "9791136202772") @PathVariable(name = "book_isbn") Long book_isbn) {
        ResponseEntity responseEntity = null;
        String url = BASE_FLASK_URL + "/book/" + book_isbn + "/topics";
        try {
            BookDto bookDto = bookService.selectBook(book_isbn);
            if (bookDto == null) {
                throw new IllegalStateException("잘못된 책 ISBN 입니다");
            }
            responseEntity =  ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
        } catch (Exception exception) {
            // 잘못된 책 ISBN인 경우
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
