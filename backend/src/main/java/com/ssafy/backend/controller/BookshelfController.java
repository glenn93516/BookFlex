package com.ssafy.backend.controller;

import com.ssafy.backend.dto.BookDto;
import com.ssafy.backend.dto.NoticeDto;
import com.ssafy.backend.dto.UserBookDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.BookshelfService;
import com.ssafy.backend.service.NoticeService;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookshelf")
public class BookshelfController {
    private final BookshelfService bookshelfService;
    private final UserService userService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /*
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "유저 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "읽음 조회")
    @GetMapping("/{userNickname}")
    public ResponseEntity selectBookList(@ApiParam(value = "유저 닉네임", required = true) @PathVariable("userNickname") String userNickname){
        ResponseEntity responseEntity = null;
        try {
            UserDto findUser = userService.findUserByUserNickname(userNickname);

            List<BookDto> BookDtoList = bookshelfService.selectBookList(findUser.getUserId());
            ListDataResponse<BookDto> response = responseService.getListDataResponse(true, "읽음 조회 성공", BookDtoList);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "유저 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "읽음 조회")
    @GetMapping("/{userNickname}")
    public ResponseEntity selectUserBookList(@ApiParam(value = "유저 닉네임", required = true) @PathVariable("userNickname") String userNickname){
        ResponseEntity responseEntity = null;
        try {
            UserDto findUser = userService.findUserByUserNickname(userNickname);
            List<UserBookDto> userBookDtoList = bookshelfService.selectUserBookList(findUser.getUserId());

            List<BookDto> bookDtoList = bookshelfService.selectBookList(userBookDtoList);
            ListDataResponse<BookDto> response = responseService.getListDataResponse(true, "읽음 조회 성공", bookDtoList);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "유저 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "읽음 작성", notes = " 현재는 유저도 작성 가능 차후 수정 예정 ")
    @PostMapping
    public ResponseEntity insertUserBook(@ApiIgnore final Authentication authentication,
                                      @ApiParam(value = "읽음 정보", required = true) @RequestBody UserBookDto userBookDto){
        ResponseEntity responseEntity = null;
        try {
            bookshelfService.insertUserBook(userBookDto);
            BaseResponse response = responseService.getBaseResponse(true, "읽음 생성 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "유저 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "읽음 삭제")
    @DeleteMapping("/{userBookId}")
    public ResponseEntity deleteUserBook(@ApiParam(value = "읽음 아이디", required = true) @PathVariable("userBookId") Long userBookId){
        ResponseEntity responseEntity = null;
        try {
            bookshelfService.deleteUserBook(userBookId);
            BaseResponse response = responseService.getBaseResponse(true, "읽음 삭제 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
