package com.ssafy.backend.mapper;

import com.ssafy.backend.dto.ReceivemsgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReceivemsgMapper {
    List<ReceivemsgDto> selectReceivemsgList(Long receiverId) throws Exception;
    Optional<ReceivemsgDto> selectReceivemsg(Long receivemsgId) throws Exception;

    int updateReceivemsg(Long receivemsgId) throws Exception;
    int insertReceivemsg(ReceivemsgDto receivemsgDto) throws Exception;
    int deleteReceivemsg(@Param("receivemsgId") Long receivemsgId, @Param("userId") Long userId) throws Exception;
}
