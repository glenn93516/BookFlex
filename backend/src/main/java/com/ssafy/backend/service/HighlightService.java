package com.ssafy.backend.service;

import com.ssafy.backend.dto.highlight.HighlightDto;
import com.ssafy.backend.mapper.HighlightMapper;
import com.ssafy.backend.mapper.UserBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
