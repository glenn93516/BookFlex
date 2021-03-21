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

    @Transactional
    public void saveGenres(Long userId, List<GenreDto> genres) {
        List<GenreDto> userGenres = genreMapper.findByUserId(userId);

        // 전달받은 장르 리스트 중 이미 유저가 등록한 장르는 제외
        for (int i = 0; i < genres.size(); ++i) {
            GenreDto genre = genres.get(i);
            for (GenreDto userGenre : userGenres) {
                if (genre.getGenreId().equals(userGenre.getGenreId())) {
                    genres.remove(i);
                    --i;
                    break;
                }
            }
        }

        if (genres != null && genres.size() > 0) {
            genreMapper.saveGenres(userId, genres);
        }
    }

    @Transactional
    public void removeGenre(Long userId, GenreDto genre) {
        genreMapper.deleteUserGenre(userId, genre.getGenreId());
    }
}
