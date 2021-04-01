package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.SendmsgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SendmsgMapper {
    List<SendmsgDto> selectSendmsgList(Long senderId) throws Exception;
    SendmsgDto selectSendmsg(Long sendmsgId) throws Exception;

    int insertSendmsg(SendmsgDto sendmsgDto) throws Exception;
    int deleteSendmsg(Long sendmsgId) throws Exception;
}
