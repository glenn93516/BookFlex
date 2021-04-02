package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.WishlistDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WishlistMapper {
    int insertWishlist(WishlistDto book) throws Exception;
    int deleteWishlist(Map map) throws Exception;
    List<Map> selectWishlistList(long book_isbn) throws Exception;
}
