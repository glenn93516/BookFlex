package com.ssafy.backend.service;

import com.ssafy.backend.dto.ReceivemsgDto;
import com.ssafy.backend.mapper.ReceivemsgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceivemsgService {

    private final ReceivemsgMapper receivemsgMapper;

    @Transactional
    public List<ReceivemsgDto> selectReceivemsgList(Long receiverId) throws Exception {
        return receivemsgMapper.selectReceivemsgList(receiverId);
    }

    @Transactional
    public ReceivemsgDto selectReceivemsg(Long receivemsgId) throws Exception {
        return receivemsgMapper.selectReceivemsg(receivemsgId);
    }

    @Transactional
    public int deleteReceivemsg(Long receivemsgId) throws Exception {
        return receivemsgMapper.deleteReceivemsg(receivemsgId);
    }
}
