package com.ssafy.backend.service;

import com.ssafy.backend.dto.WishlistDto;
import com.ssafy.backend.mapper.WishlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    private WishlistMapper mapper;

    @Override
    public int insertWishlist(WishlistDto wishlist) throws Exception {
        return mapper.insertWishlist(wishlist);
    }

    @Override
    public int deleteWishlist(Map map) throws Exception {
        return mapper.deleteWishlist(map);
    }

    @Override
    public List<WishlistDto> selectWishlistList(long user_id) throws Exception {
        return mapper.selectWishlistList(user_id);
    }
}
