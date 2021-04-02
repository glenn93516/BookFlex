package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.service.EmailService;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/mail")
    @ApiOperation(value = "인증 이메일 전송", notes = "성공시 인증 코드 반환")
    public ResponseEntity getAuthEmail(@ApiParam(value = "가입 신청한 유저 이메일", required = true) @RequestParam(required = true) String userEmail) {
        ResponseEntity responseEntity = null;
        try {
            String code = emailService.sendSimpleMessage(userEmail);
            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, "인증 메일 전송 성공", code);

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (MessagingException | MailSendException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, "인증메일 전송 실패");

            responseEntity = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return responseEntity;
    }

    @PostMapping("/password")
    @ApiOperation(value = "비밀번호 초기화", notes = "성공시 새로 설정된 비밀번호 전달")
    public ResponseEntity initPassword(@ApiParam(value = "유저 이메일", required = true) @RequestParam(required = true) String userEmail) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findByUserEmail(userEmail);
            String newPassword = EmailService.createKey();
            userService.saveNewPassword(user, newPassword);

            emailService.sendNewPassword(userEmail, newPassword);
            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, "메일 전송 성공", newPassword);

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (MessagingException | MailSendException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, "메일 전송 실패");

            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }
}
