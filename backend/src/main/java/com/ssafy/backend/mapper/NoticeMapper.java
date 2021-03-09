package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDto> selectNoticeList();
    NoticeDto selectNotice(Long noticeId);

    int insertNotice(NoticeDto noticeDto);
    int updateNotice(NoticeDto noticeDto);
    int deleteNotice(Long noticeId);
}
