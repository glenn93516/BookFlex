package com.ssafy.backend.controller;

import com.ssafy.backend.dto.NoticeDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.NoticeService;
import com.ssafy.backend.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final ResponseService responseService;

    @PostMapping
    public ResponseEntity writeNotice(@RequestBody NoticeDto noticeDto){
        ResponseEntity responseEntity = null;
        try {
            noticeService.writeNotice(noticeDto);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 작성 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity listNotice(){
        ResponseEntity responseEntity = null;
        try {
            List<NoticeDto> noticeDtoList = noticeService.listNotice();
            ListDataResponse<NoticeDto> response = responseService.getListDataResponse(true, "공지사항 전체 조회 성공", noticeDtoList);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity getNotice(@PathVariable("noticeId") int noticeId){
        ResponseEntity responseEntity = null;
        try {
            NoticeDto noticeDto = noticeService.getNotice(noticeId);
            SingleDataResponse<NoticeDto> response = responseService.getSingleDataResponse(true, "공지사항 상세 조회 성공", noticeDto);

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity modifyNotice(@RequestBody NoticeDto noticeDto){
        ResponseEntity responseEntity = null;
        try {
            noticeService.modifyNotice(noticeDto);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 수정 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity deleteNotice(@PathVariable("noticeId") int noticeId){
        ResponseEntity responseEntity = null;
        try {
            noticeService.deleteNotice(noticeId);
            BaseResponse response = responseService.getBaseResponse(true, "공지사항 삭제 성공");

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
