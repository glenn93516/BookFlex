package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.GenreDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GenreMapper {
    List<GenreDto> findAll();
}
