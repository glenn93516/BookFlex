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

    public void writeNotice(NoticeDto noticeDto){
        noticeMapper.writeNotice(noticeDto);
    }

    public List<NoticeDto> listNotice(){
        return noticeMapper.listNotice();
    }

    public NoticeDto getNotice(int noticeId){
        return noticeMapper.getNotice(noticeId);
    }

    public void modifyNotice(NoticeDto noticeDto){ noticeMapper.modifyNotice(noticeDto); }

    public void deleteNotice(int noticeId){
        noticeMapper.deleteNotice(noticeId);
    }
}
