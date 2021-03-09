package com.ssafy.backend.controller;

import com.ssafy.backend.dto.LoginDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.exception.DuplicatedUsernameException;
import com.ssafy.backend.exception.LoginFailedException;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.UserService;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
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
     * TODO: 유저 프로필 이미지 수정은 따로 만들 예정
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 수정", notes = "유저 프로필 사진, 유저 이메일을 제외한 회원 정보 수정")
    @PutMapping
    public ResponseEntity modifyUser(@ApiIgnore final Authentication authentication,
                                     @ApiParam(value = "수정된 회원 정보", required = true) @RequestBody UserDto modifyUser) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);

            userService.updateUser(findUser, modifyUser);

            BaseResponse response = responseService.getBaseResponse(true, "수정 성공");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.debug(exception.getMessage());
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
    @DeleteMapping
    public ResponseEntity deleteUser(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();

            userService.deleteOne(userId);

            BaseResponse response = responseService.getBaseResponse(true, "삭제 성공");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 조회", notes = "로그인한 유저 정보 반환", response = UserDto.class)
    @GetMapping
    public ResponseEntity findUserByUserId(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "조회 성공", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserNotFoundException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
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
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }
}
