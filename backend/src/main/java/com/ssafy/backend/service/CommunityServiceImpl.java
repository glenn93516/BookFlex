package com.ssafy.backend.service;

import com.ssafy.backend.mapper.CommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService{

    @Autowired
    CommunityMapper mapper;

    @Override
    public List<Map> getMaxReadingBook() throws Exception {
        return mapper.selectMaxReadingBook();
    }

    @Override
    public List<Map> getMaxGoodBook() throws Exception {
        return mapper.selectMaxGoodBook();
    }

    @Override
    public List<Map> getMaxHighlightBook() throws Exception {
        return mapper.selectMaxHighlightBook();
    }

    @Override
    public List<Map> getMaxWishlistBook() throws Exception {
        return mapper.selectMaxWishlistBook();
    }
}
