package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.highlight.HighlightDto;
import com.ssafy.backend.dto.highlight.HighlightRequestDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.exception.UserNotFoundException;
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
        } catch (IOException | IllegalArgumentException | IllegalStateException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저가 작성한 문장 수집 조회")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity getUserHighlights(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();

            // 작성한 문장수집 조회
            List<HighlightDto> highlights = highlightService.findAllByUserId(userId);

            ListDataResponse<HighlightDto> response = responseService.getListDataResponse(true, "조회 성공", highlights);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return responseEntity;
    }
}
