package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.GenreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<GenreDto> findAll();
    List<GenreDto> findByUserId(Long userId);
    void saveGenres(@Param("userId") Long userId, @Param("genres") List<GenreDto> genres);
}
