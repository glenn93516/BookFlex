package com.ssafy.backend.service;

import com.ssafy.backend.dto.ReceivemsgDto;
import com.ssafy.backend.dto.SendmsgDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.mapper.ReceivemsgMapper;
import com.ssafy.backend.mapper.SendmsgMapper;
import com.ssafy.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SendmsgService {

    private final UserMapper userMapper;
    private final SendmsgMapper sendmsgMapper;
    private final ReceivemsgMapper receivemsgMapper;

    @Transactional
    public List<SendmsgDto> selectSendmsgList(Long senderId) throws Exception {
        List<SendmsgDto> sendmsgDtoList =  sendmsgMapper.selectSendmsgList(senderId);

        for (SendmsgDto sendmsgDto : sendmsgDtoList) {
            UserDto findUser = userMapper.findUserByUserId(sendmsgDto.getReceiverId())
                    .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
            sendmsgDto.setReceiverNickname(findUser.getUserNickname());
        }

        return sendmsgDtoList;
    }

    @Transactional
    public SendmsgDto selectSendmsg(Long sendmsgId) throws Exception {
        // 읽음 표시
        SendmsgDto sendmsgDto = sendmsgMapper.selectSendmsg(sendmsgId);

        UserDto findUser = userMapper.findUserByUserId(sendmsgDto.getReceiverId())
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
        sendmsgDto.setReceiverNickname(findUser.getUserNickname());

        return sendmsgDto;
    }

    @Transactional
    public int insertSendmsg(SendmsgDto sendmsgDto) throws Exception {
        receivemsgMapper.insertReceivemsg(new ReceivemsgDto(
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
