package com.ssafy.backend.controller;

import com.ssafy.backend.dto.ReceivemsgDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.ReceivemsgService;
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
@RequestMapping("/receivemsg")
public class ReceivemsgController {

    private final ReceivemsgService receivemsgService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(ReceivemsgController.class);

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "수신 메세지 조회")
    @GetMapping
    public ResponseEntity selectReceivemsgList(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            List<ReceivemsgDto> receivemsgDtoList = receivemsgService.selectReceivemsgList(userId);

            ListDataResponse<ReceivemsgDto> response = responseService.getListDataResponse(true, "수신 메세지 조회 성공", receivemsgDtoList);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "수신 메세지 상세조회")
    @GetMapping("/{receivemsgId}")
    public ResponseEntity selectReceivemsg(@ApiIgnore final Authentication authentication,
                                           @ApiParam(value = "수신메세지 아이디", required = true) @PathVariable Long receivemsgId) {
        ResponseEntity responseEntity = null;
        try {
            Long loginUserId = ((UserDto) authentication.getPrincipal()).getUserId();

            ReceivemsgDto receivemsgDto = receivemsgService.selectReceivemsg(receivemsgId);
            Long receiverId = receivemsgDto.getReceiverId();

            if (!loginUserId.equals(receiverId)) {
                throw new IllegalStateException("본인에게 온 메세지만 확인 가능합니다");
            }

            SingleDataResponse<ReceivemsgDto> response = responseService.getSingleDataResponse(true, "수신 메세지 상세조회 성공", receivemsgDto);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "수신 메세지 삭제")
    @DeleteMapping("/{receivemsgId}")
    public ResponseEntity deleteReceivemsg(@ApiIgnore final Authentication authentication,
                                           @ApiParam(value = "수신메세지 아이디", required = true) @PathVariable Long receivemsgId) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            receivemsgService.deleteReceivemsg(receivemsgId, userId);

            BaseResponse response = responseService.getBaseResponse(true, "수신 메세지 삭제 성공");
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }
}
