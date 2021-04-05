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
    void updateOne(HighlightDto origin);
    List<HighlightDto> findAllOnlyPublic();
    int findGoodCountByHighlightId(Long highlightId);
    boolean checkUserGoodByHighlightIdAndUserId(@Param("highlightId") Long highlightId, @Param("userId") Long userId);
    void pressGood(@Param("highlightId") Long highlightId, @Param("userId") Long userId);
    void cancelGood(@Param("highlightId") Long highlightId, @Param("userId") Long userId);
    List<HighlightDto> findAllByUserIdOnlyGood(Long userId);
    Optional<HighlightDto> findOneByUserIdOrderByUpdatedDate(Long userId);
}
