package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    void writeNotice(NoticeDto noticeDto);
    List<NoticeDto> listNotice();
    NoticeDto getNotice(int noticeId);
    void modifyNotice(NoticeDto noticeDto);
    void deleteNotice(int noticeId);
}
