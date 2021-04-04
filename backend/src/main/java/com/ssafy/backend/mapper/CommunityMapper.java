package com.ssafy.backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    List<Map> selectMaxReadingBook() throws Exception;
    List<Map> selectMaxGoodBook() throws Exception;
    List<Map> selectMaxHighlightBook() throws Exception;
    List<Map> selectMaxWishlistBook() throws Exception;
}
