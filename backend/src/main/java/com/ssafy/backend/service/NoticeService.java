package com.ssafy.backend.service;

import com.ssafy.backend.dto.NoticeDto;
import com.ssafy.backend.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    @Transactional
    public List<NoticeDto> listNotice() throws Exception {
        return noticeMapper.selectNoticeList();
    }

    @Transactional
    public NoticeDto getNotice(Long noticeId) throws Exception {
        return noticeMapper.selectNotice(noticeId);
    }

    @Transactional
    public int writeNotice(NoticeDto noticeDto) throws Exception {
        if(noticeDto.getNoticeTitle() == null || noticeDto.getNoticeContent() == null) {
            throw new Exception("제목 또는 내용이 비어있습니다.");
        }
        return noticeMapper.insertNotice(noticeDto);
    }

    @Transactional
    public int modifyNotice(NoticeDto noticeDto) throws Exception {
        return noticeMapper.updateNotice(noticeDto);
    }

    @Transactional
    public int deleteNotice(Long noticeId) throws Exception {
        return noticeMapper.deleteNotice(noticeId);
    }
}
