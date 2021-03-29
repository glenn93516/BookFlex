package com.ssafy.backend.service;

import com.ssafy.backend.dto.ReceivemsgDto;
import com.ssafy.backend.dto.SendmsgDto;
import com.ssafy.backend.mapper.ReceivemsgMapper;
import com.ssafy.backend.mapper.SendmsgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendmsgService {

    private final SendmsgMapper sendmsgMapper;
    private final ReceivemsgMapper receivemsgMapper;

    @Transactional
    public List<SendmsgDto> selectSendmsgList(Long senderId) throws Exception {
        return sendmsgMapper.selectSendmsgList(senderId);
    }

    @Transactional
    public SendmsgDto selectSendmsg(Long sendmsgId) throws Exception {
        return sendmsgMapper.selectSendmsg(sendmsgId);
    }

    @Transactional
    public int insertSendmsg(SendmsgDto sendmsgDto) throws Exception {
        receivemsgMapper.insertReceivemsg(new ReceivemsgDto(
                sendmsgDto.getSendmsgTitle(),
                sendmsgDto.getSendmsgContent(),
                sendmsgDto.getSenderId(),
                sendmsgDto.getReceiverId()
        ));
        return sendmsgMapper.insertSendmsg(sendmsgDto);
    }

    @Transactional
    public int deleteSendmsg(Long sendmsgId) throws Exception {
        return sendmsgMapper.deleteSendmsg(sendmsgId);
    }
}
