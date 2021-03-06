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

    @ApiOperation(value = "?????? ??????")
    @PostMapping("/join")
    public ResponseEntity join(@ApiParam(value = "?????? ?????? ??????", required = true) @RequestBody UserDto userDto) {
        ResponseEntity responseEntity = null;
        try {
            UserDto savedUser = userService.join(userDto);
            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "???????????? ??????", savedUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicatedUsernameException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    @ApiOperation(value = "?????????", notes = "????????? ????????? ?????? ??????")
    @PostMapping("/login")
    public ResponseEntity login(@ApiParam(value = "?????? ?????????, ????????????", required = true) @RequestBody LoginDto loginDto) {
        ResponseEntity responseEntity = null;
        try {
            String token = userService.login(loginDto);

            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.info("{} ????????? ????????? ??????????????????", loginDto.getUserEmail());
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + token);

            SingleDataResponse<String> response = responseService.getSingleDataResponse(true, "????????? ??????", token);

            responseEntity = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
        } catch (LoginFailedException exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    /**
     * ?????? ?????? ??????
     * ????????? ?????? ?????? & ??????
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "????????? ?????? ??? ???????????? token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "?????? ?????? ??????", notes = "?????? ????????? ??????, ?????? ???????????? ????????? ?????? ?????? ??????")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping
    public ResponseEntity modifyUser(@ApiIgnore final Authentication authentication,
                                     @ApiParam(value = "????????? ?????? ??????", required = true) UserDto modifyUser) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);
            // ????????? ?????? S3??? ??????
            if (modifyUser.getUserProfileImgFile() != null) {
                String unique = "profile_" + findUser.getUserId() + "_" + LocalDateTime.now(ZoneId.of("Asia/Seoul"));
                String url = uploader.upload(modifyUser.getUserProfileImgFile(), "user", unique);

                modifyUser.setUserProfileImg(url);
            }

            userService.updateUser(findUser, modifyUser);

            BaseResponse response = responseService.getBaseResponse(true, "?????? ??????");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException | IOException | IllegalArgumentException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    /**
     * ?????? ?????? ?????? (?????? ??????)
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "????????? ?????? ??? ???????????? token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "?????? ??????")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity deleteUser(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();

            userService.deleteOne(userId);

            BaseResponse response = responseService.getBaseResponse(true, "?????? ??????");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "????????? ?????? ??? ???????????? token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "?????? ?????? ??????", notes = "???????????? ?????? ?????? ??????", response = UserDto.class)
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity findUserByUserId(@ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;
        try {
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId();
            UserDto findUser = userService.findByUserId(userId);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "?????? ??????", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "??????????????? ?????? ??????", notes = "?????? ????????? ?????? ?????? ?????? ??????", response = UserDto.class)
    @GetMapping("/{userNickname}")
    public ResponseEntity findUserByUserNickname(
            @ApiParam(value = "?????????", required = true) @PathVariable String userNickname) {
        ResponseEntity responseEntity = null;
        try {
            UserDto findUser = userService.findUserByUserNickname(userNickname);

            SingleDataResponse<UserDto> response = responseService.getSingleDataResponse(true, "?????? ??????", findUser);

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "????????? ?????? ??????", notes = "?????? ???????????? ????????? ?????? ????????? ??????(????????? ?????? success: false)", response = UserDto.class)
    @GetMapping("/check")
    public ResponseEntity checkDuplicateEmail(@ApiParam(value = "?????????", required = true) @RequestParam String userEmail) {
        ResponseEntity responseEntity = null;
        try {
            userService.checkDuplicateUser(userEmail);
            BaseResponse response = responseService.getBaseResponse(true, "?????? ???????????? ????????? ????????? ????????????");

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DuplicatedUsernameException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return responseEntity;
    }

    /**
     * ????????? ????????? ???????????? ?????? ??????
     */
    @ApiOperation(value = "????????? ????????? ?????? ????????? ?????? ???????????? ??????", notes = "onlyGood??? True??? ?????? ????????? ????????? ?????? ??? ??????")
    @GetMapping("/{userNickname}/highlight")
    public ResponseEntity getUserHighlights(@ApiParam(value = "????????? ?????? ?????????", required = true) @PathVariable String userNickname,
                                            @ApiParam(value = "?????? ?????? ???????????? 1?????? ??????", required = false) @RequestParam(required = true, defaultValue = "false") Boolean onlyRecent,
                                            @ApiParam(value = "????????? ?????? ??????????????? ??????", required = false) @RequestParam(required = true, defaultValue = "false") Boolean onlyGood) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findUserByUserNickname(userNickname);

            // ????????? ???????????? ??????
            List<HighlightDto> highlights = new ArrayList<>();
            if (onlyGood) {
                highlights = highlightService.findAllByUserIdOnlyGood(user.getUserId());
            } else {
                highlights = highlightService.findAllByUserId(user.getUserId());
            }

            ListDataResponse<HighlightDto> response = responseService.getListDataResponse(true, "?????? ??????", highlights);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }

    /**
     * ????????? ????????? ????????? ???????????? ??????
     */
    @ApiOperation(value = "????????? ??????????????? ????????? ???????????? ??????")
    @GetMapping("/{userNickname}/highlight/recent")
    public ResponseEntity getUserRecentHighlight(@ApiParam(value = "????????? ?????? ?????????", required = true) @PathVariable String userNickname) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findUserByUserNickname(userNickname);

            // ????????? ???????????? ??????
            HighlightDto highlight = highlightService.findOneByUserIdOrderByUpdatedDate(user.getUserId());

            SingleDataResponse<HighlightDto> response = responseService.getSingleDataResponse(true, "?????? ??????", highlight);
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalStateException exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(true, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (UserNotFoundException exception) {
            logger.info(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return responseEntity;
    }

    /**
     * ????????? ?????? ?????? ?????? ?????? API
     */
    @ApiOperation(value = "?????? ???????????? ?????? ??????")
    @GetMapping("/{userNickname}/statistics")
    public ResponseEntity getUserGenreStatistics(@ApiParam(value = "????????? ?????? ?????????", required = true) @PathVariable String userNickname) {
        ResponseEntity responseEntity = null;
        try {
            UserDto user = userService.findUserByUserNickname(userNickname);
            Long userId = user.getUserId();
            String url = BASE_FLASK_URL + "/statistics/" + userId;

            responseEntity = ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build();
        } catch (UserNotFoundException exception) {
            // ???????????? ?????? ????????? ??????
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return responseEntity;
    }
}
