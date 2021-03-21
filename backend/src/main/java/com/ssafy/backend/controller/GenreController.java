package com.ssafy.backend.controller;

import com.ssafy.backend.dto.GenreDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.service.GenreService;
import com.ssafy.backend.service.ResponseService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(GenreController.class);

    @ApiOperation(value = "장르 목록 조회", notes = "모든 장르 목록 반환")
    @GetMapping
    public ResponseEntity getGenreList() {
        ResponseEntity responseEntity = null;
        try {
            List<GenreDto> genreDtoList = genreService.findAll();

            ListDataResponse<GenreDto> response = responseService.getListDataResponse(true, "목록 조회 성공", genreDtoList);

            responseEntity = ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.info(e.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, "서버 에러");
            responseEntity = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "선호 장르 추가", notes = "유저가 선호 장르에 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity addUserGenre(@ApiIgnore final Authentication authentication, @RequestBody List<GenreDto> genres) {
        ResponseEntity responseEntity = null;
        try {
            if (genres == null || genres.size() == 0) {
                throw new IllegalStateException("잘못된 데이터입니다");
            }
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId(); // 로그인한 유저 아이디 획득
            genreService.saveGenres(userId, genres);

            BaseResponse response = responseService.getBaseResponse(true, "장르 추가 성공");

            responseEntity = ResponseEntity.ok(response);
        } catch (IllegalStateException exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.info(e.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, "서버 에러");
            responseEntity = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return responseEntity;
    }

    @ApiOperation(value = "선호 장르 삭제", notes = "해당 장르 선호장르에서 제거")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 발급받는 token", required = true, dataType = "String", paramType = "header")
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping
    public ResponseEntity deleteUserGenre(@ApiIgnore final Authentication authentication, @RequestBody GenreDto genre) {
        ResponseEntity responseEntity = null;
        try {
            if (genre == null) {
                throw new IllegalStateException("잘못된 데이터입니다");
            }
            Long userId = ((UserDto) authentication.getPrincipal()).getUserId(); // 로그인한 유저 아이디 획득
            genreService.removeGenre(userId, genre);

            BaseResponse response = responseService.getBaseResponse(true, "장르 삭제 성공");

            responseEntity = ResponseEntity.ok(response);
        } catch (IllegalStateException exception) {
            logger.info(exception.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.info(e.getMessage());

            BaseResponse response = responseService.getBaseResponse(false, "서버 에러");
            responseEntity = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }

        return responseEntity;
    }
}
