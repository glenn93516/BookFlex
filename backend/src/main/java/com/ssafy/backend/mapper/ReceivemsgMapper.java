package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.ReceivemsgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceivemsgMapper {
    List<ReceivemsgDto> selectReceivemsgList(Long receiverId) throws Exception;
    ReceivemsgDto selectReceivemsg(Long receivemsgId) throws Exception;

    int updateReceivemsg(Long receivemsgId) throws Exception;
    int insertReceivemsg(ReceivemsgDto receivemsgDto) throws Exception;
    int deleteReceivemsg(Long receivemsgId) throws Exception;
}
