package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.SendmsgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SendmsgMapper {
    List<SendmsgDto> selectSendmsgList(Long senderId) throws Exception;
    Optional<SendmsgDto> selectSendmsg(Long sendmsgId) throws Exception;

    int insertSendmsg(SendmsgDto sendmsgDto) throws Exception;
    int deleteSendmsg(@Param("sendmsgId") Long sendmsgId, @Param("userId") Long userId) throws Exception;
}
