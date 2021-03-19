package com.ssafy.backend.controller;

import com.ssafy.backend.dto.GenreDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.service.GenreService;
import com.ssafy.backend.service.ResponseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
