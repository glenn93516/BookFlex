package com.ssafy.backend.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final String ePw = createKey();
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public static String createKey() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; ++i) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    sb.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    sb.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    sb.append((random.nextInt(10)));
                    break;
            }
        }

        return sb.toString();
    }

    public MimeMessage createMessage(String to) throws MessagingException {
        logger.debug("보내는 대상 : '{}'", to);
        logger.debug("인증 번호 : '{}'", ePw);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        message.addRecipients(Message.RecipientType.TO, to); // 이메일 받는 사람
        message.setSubject("[BookFlex] 인증번호가 도착했습니다"); // 이메일 제목

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 안녕하세요  [BookFlex]입니다. </h1>";
        msg += "<br>";
        msg += "<p>아래 코드를 인증창으로 돌아가 입력해주세요<p>";
        msg += "<br>";
        msg += "<p>감사합니다!<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>이메일인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += ePw + "</strong><div><br/> ";
        msg += "</div>";

        message.setText(msg, "utf-8", "html");

        return message;
    }

    public String sendSimpleMessage(String to) throws MessagingException {
        MimeMessage message = createMessage(to);

        javaMailSender.send(message);

        return ePw;
    }
}
