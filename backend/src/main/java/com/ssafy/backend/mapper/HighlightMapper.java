package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.highlight.HighlightDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HighlightMapper {

    void save(HighlightDto highlight);
    List<HighlightDto> findAllByUserId(@Param("userId") Long userId);
}
