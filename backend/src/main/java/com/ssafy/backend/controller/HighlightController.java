package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.highlight.HighlightDetailDto;
import com.ssafy.backend.dto.highlight.HighlightDto;
import com.ssafy.backend.dto.highlight.HighlightRequestDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.HighlightService;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.UserService;
import com.ssafy.backend.utils.Uploader;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/highlight")
public class HighlightController {

    private final ResponseService responseService;
    private final HighlightService highlightService;
    private final UserService userService;
    private final Uploader uploader;
    private final Logger logger = LoggerFactory.getLogger(HighlightController.class);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "문장 수집 등록")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity writeHighlight(@ApiIgnore final Authentication authentication,
                                         HighlightRequestDto highlightRequestDto) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            HighlightDto highlight = HighlightDto.createHighlight(highlightRequestDto, userId);
            // 배경 사진 S3에 등록
            if (highlightRequestDto.getHighlightCover() != null) {
                String unique = "highlight_" + userId + "_" + LocalDateTime.now(ZoneId.of("Asia/Seoul"));
                String url = uploader.upload(highlightRequestDto.getHighlightCover(), "highlight", unique);

                highlight.setHighlightCover(url);
            }

            // 작성한 하이라이트 DB에 저장
            highlightService.save(highlight);

            BaseResponse response = responseService.getBaseResponse(true, "등록 성공");
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException exception) {
            // 아직 읽지 않은 책 문장수집 작성하려는 경우
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | IllegalArgumentException exception) {
            // 파일 업로드 중 오류 발생
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, "서버 에러 발생");
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "공개된 문장 수집 목록 조회", notes = "공개된 문장수집 목록 조회 - 게시판 용도")
    @GetMapping
    public ResponseEntity getUserHighlights() {
        ResponseEntity responseEntity = null;
        try {
            // 작성한 문장수집 조회
            List<HighlightDto> highlights = highlightService.findAllOnlyPublic();

            ListDataResponse<HighlightDto> response = responseService.getListDataResponse(true, "조회 성공", highlights);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }


    @ApiOperation(value = "문장 수집 상세 정보조회")
    @GetMapping("/{highlightId}")
    public ResponseEntity getUserHighlights(@PathVariable Long highlightId) {
        ResponseEntity responseEntity = null;
        try {
            // 문장수집 상세정보 조회
            HighlightDetailDto highlightDetail = highlightService.findByHighlightId(highlightId);

            SingleDataResponse<HighlightDetailDto> response = responseService.getSingleDataResponse(true, "조회 성공", highlightDetail);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException exception) {
            // 문장수집 id 틀린 경우
            logger.info(exception.getMessage());
            
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저가 작성한 문장 수집 삭제")
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{highlightId}")
    public ResponseEntity deleteUserHighlights(@ApiIgnore final Authentication authentication, @PathVariable Long highlightId) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();

            // 작성한 문장수집 조회
            HighlightDto highlight = highlightService.findOneByHighlightId(highlightId);

            // 삭제 요청한 문장수집과 로그인한 유저가 같아야 삭제 가능
            if (!userId.equals(highlight.getUserId())) {
                throw new IllegalStateException("작성자만 삭제할 수 있습니다");
            }

            highlightService.deleteOne(highlightId);

            BaseResponse response = responseService.getBaseResponse(true, "삭제 성공");
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException exception) {
            // 이미 삭제된 데이터 이거나 다른 유저의 데이터 삭제하려고 시도한 경우
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저가 작성한 문장 수집 수정", notes = "내용, 페이지, 공개여부, 배경이미지 수정 가능")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{highlightId}")
    public ResponseEntity modifyUserHighlights(@ApiIgnore final Authentication authentication,
                                               @PathVariable Long highlightId,
                                               HighlightRequestDto highlightRequestDto) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            HighlightDto findHighlight = highlightService.findOneByHighlightId(highlightId);

            if (!findHighlight.getUserId().equals(userId)) {
                throw new IllegalStateException("작성자만 수정할 수 있습니다");
            }

            HighlightDto highlight = HighlightDto.createHighlight(highlightRequestDto, userId);

            // 배경 사진 있으면 S3에 등록
            if (highlightRequestDto.getHighlightCover() != null) {
                String unique = "highlight_" + userId + "_" + LocalDateTime.now(ZoneId.of("Asia/Seoul"));
                String url = uploader.upload(highlightRequestDto.getHighlightCover(), "highlight", unique);

                highlight.setHighlightCover(url);
            }

            // 수정한 문장수집 저장
            highlightService.updateOne(findHighlight, highlight);

            BaseResponse response = responseService.getBaseResponse(true, "등록 성공");
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException exception) {
            // 다른 유저 데이터 수정 시도한 경우 또는 로그인, 문장수집 데이터 조회 오류
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IOException | IllegalArgumentException exception) {
            // 파일 업로드 중 오류 발생
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }
}
