package com.ssafy.backend.service;

import com.ssafy.backend.dto.WishlistDto;

import java.util.List;
import java.util.Map;

public interface WishlistService {
    int insertWishlist(WishlistDto wishlist) throws Exception;
    int deleteWishlist(Map map) throws Exception;
    List<Map> selectWishlistList(long user_id) throws Exception;
}
