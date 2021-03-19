package com.ssafy.backend.service;

import com.ssafy.backend.dto.GenreDto;
import com.ssafy.backend.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {
    private final GenreMapper genreMapper;

    public List<GenreDto> findAll() {
        return genreMapper.findAll();
    }
}
