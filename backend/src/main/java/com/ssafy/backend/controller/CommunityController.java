package com.ssafy.backend.controller;

import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.service.CommunityService;
import com.ssafy.backend.service.ResponseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private final CommunityService communityService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(CommunityController.class);

    //좋아요 가장 많이 받은 도서의 문장 정보 및 도서 정보 조회
    @ApiOperation(value = "좋아요를 가장 많이 받은 도서")
    @GetMapping(value = "/good")
    public ResponseEntity getMaxGoodBook(){
        ResponseEntity responseEntity = null;
        List<Map> list = null;
        try {
            list = communityService.getMaxGoodBook();
            ListDataResponse<Map> response = responseService.getListDataResponse(true, "최다 좋아요 문장 및 문장 도서 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "읽음 표시가 가장 많이 된 도서")
    @GetMapping(value = "/read")
    public ResponseEntity getMaxReadBook(){
        ResponseEntity responseEntity = null;
        List<Map> list = null;
        try {
            list = communityService.getMaxReadingBook();
            ListDataResponse<Map> response = responseService.getListDataResponse(true, "최다 읽음 도서 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "문장 수집이 가장 많이 된 도서")
    @GetMapping(value = "/wishlist")
    public ResponseEntity getMaxWishlistBook(){
        ResponseEntity responseEntity = null;
        List<Map> list = null;
        try {
            list = communityService.getMaxWishlistBook();
            ListDataResponse<Map> response = responseService.getListDataResponse(true, "최다 위시리스트 도서 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "읽음 표시가 가장 많이 된 도서")
    @GetMapping(value = "/highlight")
    public ResponseEntity getMaxHighlightBook(){
        ResponseEntity responseEntity = null;
        List<Map> list = null;
        try {
            list = communityService.getMaxHighlightBook();
            ListDataResponse<Map> response = responseService.getListDataResponse(true, "최다 문장수집 도서 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
