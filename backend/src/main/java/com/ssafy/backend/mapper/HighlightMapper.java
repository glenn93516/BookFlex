package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.highlight.HighlightDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HighlightMapper {

    void save(HighlightDto highlight);
}
