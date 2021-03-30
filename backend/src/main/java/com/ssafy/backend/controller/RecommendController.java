package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.service.ResponseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {
//    private final String BASE_FLASK_URL = "https://j4f004.p.ssafy.io/ml/api/recommend";
    private final String BASE_FLASK_URL = "http://localhost:5000/ml/api/recommend";
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(RecommendController.class);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token. 로그인 안한 경우 안 넣어도 됨", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "유저 맞춤 책 추천", notes = "로그인 안한 경우 랜덤으로 추천함")
    @GetMapping
    public ResponseEntity getCustomizedBooks(@ApiIgnore final Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, BASE_FLASK_URL).build();
        } else {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            String url = BASE_FLASK_URL + "/" + userId;
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
        }
    }
}
