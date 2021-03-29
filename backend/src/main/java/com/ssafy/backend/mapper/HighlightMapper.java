package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.highlight.HighlightDetailDto;
import com.ssafy.backend.dto.highlight.HighlightDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HighlightMapper {

    void save(HighlightDto highlight);
    List<HighlightDto> findAllByUserId(@Param("userId") Long userId);
    Optional<HighlightDetailDto> findByHighlightId(Long highlightId);
    Optional<HighlightDto> findOneByHighlightId(Long highlightId);
    int deleteOne(@Param("highlightId") Long highlightId);
}
