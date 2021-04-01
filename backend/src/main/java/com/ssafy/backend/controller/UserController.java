package com.ssafy.backend.controller;

import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.highlight.HighlightDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.exception.DuplicatedUsernameException;
import com.ssafy.backend.exception.LoginFailedException;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.service.HighlightService;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.UserService;
import com.ssafy.backend.utils.JwtTokenProvider;
import com.ssafy.backend.utils.Uploader;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final String BASE_FLASK_URL = "https://j4f004.p.ssafy.io/ml/api";
//    private final String BASE_FLASK_URL = "http://localhost:5000/ml/api";
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final HighlightService highlightService;
    private final ResponseService responseService;
    private final Uploader uploader;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "회원 가입")
    @PostMapping("/join")
    public ResponseEntity join(@ApiParam(value = "회원 가입 정보", required = true) @RequestBody UserDto userDto) {
        ResponseEntity responseEntity = null;
        try {
            UserDto savedUser = userService.join(userDto);
            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "회원가입 성공", savedUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicatedUsernameException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "로그인", notes = "로그인 성공시 토큰 반환")
    @PostMapping("/login")
    public ResponseEntity login(@ApiParam(value = "유저 이메일, 비밀번호", required = true) @RequestBody LoginDto loginDto) {
        ResponseEntity responseEntity = null;
        try {
            String token = userService.login(loginDto);

            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("{} 로그인 정보를 저장했습니다", loginDto.getUserEmail());
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);

            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, "로그인 성공", token);

            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
        } catch (LoginFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    /**
     * 유저 정보 수정
     * 프로필 사진 등록 & 수정
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 수정", notes = "유저 프로필 사진, 유저 이메일을 제외한 회원 정보 수정")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    public ResponseEntity modifyUser(@ApiIgnore final Authentication authentication,
                                     @ApiParam(value = "수정된 회원 정보", required = true) UserDto modifyUser) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);
            // 프로필 사진 S3에 등록
            if (modifyUser.getUserProfileImgFile() != null) {
                String unique = "profile_" + findUser.getUserId() + "_" + LocalDateTime.now(ZoneId.of("Asia/Seoul"));
                String url = uploader.upload(modifyUser.getUserProfileImgFile(), "user", unique);

                modifyUser.setUserProfileImg(url);
            }

            userService.updateUser(findUser, modifyUser);

            BaseResponse response = responseService.getBaseResponse(true, "수정 성공");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException | IOException | IllegalArgumentException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    /**
     * 유저 정보 삭제 (회원 탈퇴)
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 탈퇴")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity deleteUser(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();

            userService.deleteOne(userId);

            BaseResponse response = responseService.getBaseResponse(true, "삭제 성공");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 조회", notes = "로그인한 유저 정보 반환", response = UserDto.class)
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity findUserByUserId(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "조회 성공", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "닉네임으로 회원 조회", notes = "조회 성공시 해당 유저 정보 반환", response = UserDto.class)
    @GetMapping("/{userNickname}")
    public ResponseEntity findUserByUserNickname(
            @ApiParam(value = "닉네임", required = true) @PathVariable String userNickname) {
        ResponseEntity responseEntity = null;
        try {
            UserDto findUser = userService.findUserByUserNickname(userNickname);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "조회 성공", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "이메일 중복 체크", notes = "해당 이메일로 가입한 유저 있는지 확인(중복된 경우 success: false)", response = UserDto.class)
    @GetMapping("/check")
    public ResponseEntity checkDuplicateEmail(@ApiParam(value = "이메일", required = true) @RequestParam String userEmail) {
        ResponseEntity responseEntity = null;
        try {
            userService.checkDuplicateUser(userEmail);
            BaseResponse response = responseService.getBaseResponse(true, "해당 이메일로 가입된 유저가 없습니다");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DuplicatedUsernameException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return responseEntity;
    }

    /**
     * 유저가 작성한 문장수집 목록 조회
     */
    @ApiOperation(value = "유저가 작성한 문장 수집 조회")
    @GetMapping("/{userNickname}/highlight")
    public ResponseEntity getUserHighlights(@ApiParam(value = "조회할 유저 닉네임", required = true) @PathVariable String userNickname,
                                            @ApiParam(value = "좋아요 누른 문장수집만 조회", required = false) @RequestParam(required = true, defaultValue = "false") Boolean onlyGood) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findUserByUserNickname(userNickname);

            // 작성한 문장수집 조회
            List<HighlightDto> highlights = new ArrayList<>();
            if (onlyGood) {
                highlights = highlightService.findAllByUserIdOnlyGood(user.getUserId());
            } else {
                highlights = highlightService.findAllByUserId(user.getUserId());
            }

            ListDataResponse<HighlightDto> response = responseService.getListDataResponse(true, "조회 성공", highlights);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }

    /**
     * 유저의 선호 장르 통계 조회 API
     */
    @ApiOperation(value = "유저 선호장르 통계 조회")
    @GetMapping("/{userNickname}/statistics")
    public ResponseEntity getUserGenreStatistics(@ApiParam(value = "조회할 유저 닉네임", required = true) @PathVariable String userNickname) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findUserByUserNickname(userNickname);
            Long userId = user.getUserId();
            String url = BASE_FLASK_URL + "/statistics/" + userId;

            responseEntity = ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
        } catch (UserNotFoundException exception) {
            // 존재하지 않는 유저인 경우
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }
}
