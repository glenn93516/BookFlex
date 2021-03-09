package com.ssafy.backend.controller;

import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.EmailService;
import com.ssafy.backend.service.ResponseService;
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
import javax.mail.SendFailedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @PostMapping("/mail")
    public ResponseEntity getAuthEmail(@RequestParam(required = true) String userEmail) {
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
}
