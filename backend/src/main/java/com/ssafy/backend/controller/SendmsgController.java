package com.ssafy.backend.controller;

import com.ssafy.backend.dto.MessageDto;
import com.ssafy.backend.dto.SendmsgDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.*;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.SendmsgService;
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
@RequestMapping("/sendmsg")
public class SendmsgController {

    private final SendmsgService sendmsgService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(ReceivemsgController.class);

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "발신 메세지 조회")
    @GetMapping
    public ResponseEntity selectSendmsgList(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            List<SendmsgDto> sendmsgDtoList = sendmsgService.selectSendmsgList(userId);

            ListDataResponse<SendmsgDto> response = responseService.getListDataResponse(true, "발신 메세지 조회 성공", sendmsgDtoList);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "발신 메세지 상세조회")
    @GetMapping("/{sendmsgId}")
    public ResponseEntity selectSendmsg(@ApiParam(value = "발신메세지 아이디", required = true)
                                        @PathVariable Long sendmsgId) {
        ResponseEntity responseEntity = null;

        try {
            SendmsgDto sendmsgDto = sendmsgService.selectSendmsg(sendmsgId);

            SingleDataResponse<SendmsgDto> response = responseService.getSingleDataResponse(true, "발신 메세지 조회 성공", sendmsgDto);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "메세지 생성")
    @PostMapping
    public ResponseEntity insertMessage(@ApiIgnore final Authentication authentication,
                                        @ApiParam(value = "메세지 정보", required = true)
                                        @RequestBody SendmsgDto sendmsgDto) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            sendmsgDto.setSenderId(userId);
            sendmsgService.insertSendmsg(sendmsgDto);

            BaseResponse response = responseService.getBaseResponse(true, "메세지 생성 성공");
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "발신 메세지 상세조회")
    @DeleteMapping("/{sendmsgId}")
    public ResponseEntity deleteendmsg(@ApiParam(value = "발신메세지 아이디", required = true)
                                        @PathVariable Long sendmsgId) {
        ResponseEntity responseEntity = null;

        try {
            sendmsgService.deleteSendmsg(sendmsgId);

            BaseResponse response = responseService.getBaseResponse(true, "메세지 삭제 성공");
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return  responseEntity;
    }
}
