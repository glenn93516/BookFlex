package com.ssafy.backend.controller;

import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.dto.WishlistDto;
import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import com.ssafy.backend.service.ResponseService;
import com.ssafy.backend.service.WishlistService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private final WishlistService wishlistService;
    private final ResponseService responseService;
    private final Logger logger = LoggerFactory.getLogger(WishlistController.class);

    //위시리스트 등록
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/{book_isbn}")
    public ResponseEntity addWishlist(@PathVariable(name = "book_isbn") long book_isbn, @ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String wishlist_date = format.format(time);
        WishlistDto wishlist = new WishlistDto();
        wishlist.setWishlist_date(wishlist_date);
        wishlist.setBook_isbn(book_isbn);
        Long user_id = ((UserDto) authentication.getPrincipal()).getUserId();
        wishlist.setUser_id(user_id);

        try {
            if(wishlistService.insertWishlist(wishlist)== 1) {
                BaseResponse response = responseService.getBaseResponse(true, "위시리스트 등록 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
        }
//        catch (DataIntegrityViolationException exception){
//            logger.debug(exception.getMessage());
//            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
//            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
        catch (Exception exception) {
            logger.debug(exception.getMessage());
//            exception.printStackTrace();
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //위시리스트 삭제
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping(value = "/{wishlist_id}")
    public ResponseEntity deleteWishlist(@PathVariable(name = "wishlist_id") long wishlist_id, @ApiIgnore final Authentication authentication) {
        ResponseEntity responseEntity = null;
        Long user_id = ((UserDto) authentication.getPrincipal()).getUserId();
        Map map = new HashMap();
        map.put("wishlist_id",wishlist_id);
        map.put("user_id",user_id);
        try {
            if (wishlistService.deleteWishlist(map)== 1) {
                BaseResponse response = responseService.getBaseResponse(true, "위시리스트 삭제 성공");
                responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            } else {
                BaseResponse response = responseService.getBaseResponse(false, "일치 위시리스트 없음");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }

    //위시리스트 조회
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "")
    public ResponseEntity getWishList(@ApiIgnore final Authentication authentication){
        ResponseEntity responseEntity = null;
        List<WishlistDto> list = null;
        Long user_id = ((UserDto) authentication.getPrincipal()).getUserId();
        try {
            list = wishlistService.selectWishlistList(user_id);
            ListDataResponse<WishlistDto> response = responseService.getListDataResponse(true, "위시리스트 조회 성공", list);
            responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception exception) {
            logger.debug(exception.getMessage());
            BaseResponse response = responseService.getBaseResponse(false, exception.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return responseEntity;
    }
}
