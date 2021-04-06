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
        HighlightDetailDto highlight = highlightMapper.findByHighlightId(highlightId)
                .orElseThrow(() -> new IllegalStateException("잘못된 문장수집 id 입니다"));

        int goodCount = highlightMapper.findGoodCountByHighlightId(highlightId);
        highlight.setGoodCount(goodCount);

        return highlight;
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

    public List<HighlightDto> findAllOnlyPublic() {
        return highlightMapper.findAllOnlyPublic();
    }

    public boolean checkUserGoodByHighlightIdAndUserId(Long highlightId, Long userId) {
        return highlightMapper.checkUserGoodByHighlightIdAndUserId(highlightId, userId);
    }

    @Transactional
    public void pressGood(Long highlightId, Long userId, boolean userGood) {
        if (userGood) {
            // 이미 좋아요 누른 경우 좋아요 취소
            highlightMapper.cancelGood(highlightId, userId);
        } else {
            // 아직 좋아요 안누른 경우 좋아요
            highlightMapper.pressGood(highlightId, userId);
        }
    }

    public List<HighlightDto> findAllByUserIdOnlyGood(Long userId) {
        return highlightMapper.findAllByUserIdOnlyGood(userId);
    }

    public HighlightDto findOneByUserIdOrderByUpdatedDate(Long userId) {
        return highlightMapper.findOneByUserIdOrderByUpdatedDate(userId)
                .orElseThrow(() -> new IllegalStateException("작성한 문장수집이 없습니다"));
    }
}
