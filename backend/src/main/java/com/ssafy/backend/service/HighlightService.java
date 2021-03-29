package com.ssafy.backend.service;

import com.ssafy.backend.dto.highlight.HighlightDetailDto;
import com.ssafy.backend.dto.highlight.HighlightDto;
import com.ssafy.backend.mapper.HighlightMapper;
import com.ssafy.backend.mapper.UserBookMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HighlightService {

    private final HighlightMapper highlightMapper;
    private final UserBookMapper userBookMapper;

    @Transactional
    public void save(HighlightDto highlight) {
        userBookMapper.findUserBookByUserIdAndBookIsbn(highlight.getUserId(), highlight.getBookIsbn())
                .orElseThrow(() -> new IllegalStateException("아직 읽지 않은 책입니다"));

        highlightMapper.save(highlight);
    }

    public List<HighlightDto> findAllByUserId(Long userId) {
        return highlightMapper.findAllByUserId(userId);
    }

    public HighlightDetailDto findByHighlightId(Long highlightId) {
        return highlightMapper.findByHighlightId(highlightId)
                .orElseThrow(() -> new IllegalStateException("잘못된 문장수집 id 입니다"));
    }

    public HighlightDto findOneByHighlightId(Long highlightId) {
        return highlightMapper.findOneByHighlightId(highlightId)
                .orElseThrow(() -> new IllegalStateException("잘못된 문장수집 id 입니다"));
    }

    @Transactional
    public void deleteOne(Long highlightId) {
        int result = highlightMapper.deleteOne(highlightId);

        if (result == 0) {
            throw new IllegalStateException("이미 삭제된 문장수집입니다");
        }
    }

    @Transactional
    public void updateOne(HighlightDto origin, HighlightDto newData) {
        origin.update(newData);

        highlightMapper.updateOne(origin);
    }

}
