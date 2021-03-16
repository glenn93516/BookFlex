package com.ssafy.backend.controller;

import com.ssafy.backend.dto.NoticeDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.NoticeService;
import com.ssafy.backend.service.ResponseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "공지사항 조회")
    @GetMapping
    public ResponseEntity selectNoticeList(){
        ResponseEntity responseEntity = null;
        try {
            List<NoticeDto> noticeDtoList = noticeService.listNotice();
            ListDataResponse<NoticeDto> response = responseService.getListDataResponse(true, "공지사항 전체 조회 성공", noticeDtoList);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "공지사항 상세조회")
    @GetMapping("/{noticeId}")
    public ResponseEntity selectNotice(@ApiParam(value = "공지사항 아이디", required = true, example = "123") @PathVariable("noticeId") Long noticeId){
        ResponseEntity responseEntity = null;
        try {
            NoticeDto noticeDto = noticeService.getNotice(noticeId);
            SingleDataResponse<NoticeDto> response = responseService.getSingleDataResponse(true, "공지사항 상세 조회 성공", noticeDto);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "관리자 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "공지사항 작성", notes = "관리자 계정으로만 사용 가능")
    @PostMapping
    public ResponseEntity insertNotice(@ApiIgnore final Authentication authentication,
                                       @ApiParam(value = "공지사항 정보", required = true) @RequestBody NoticeDto noticeDto){
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            noticeDto.setUserId(userId);

            noticeService.writeNotice(noticeDto);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 작성 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "관리자 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "공지사항 수정", notes = "관리자 계정으로만 사용 가능")
    @PutMapping("/{noticeId}")
    public ResponseEntity updateNotice(@ApiIgnore final Authentication authentication,
                                       @ApiParam(value = "공지사항 정보", required = true) @RequestBody NoticeDto noticeDto){
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            noticeDto.setUserId(userId);

            noticeService.modifyNotice(noticeDto);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 수정 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "관리자 로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")})
    @ApiOperation(value = "공지사항 삭제", notes = "관리자 계정으로만 사용 가능")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity deleteNotice(@ApiParam(value = "공지사항 아이디", required = true, example = "123") @PathVariable("noticeId") Long noticeId){
        ResponseEntity responseEntity = null;
        try {
            noticeService.deleteNotice(noticeId);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 삭제 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
