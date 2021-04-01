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
@Transactional(readOnly = true)
public class SendmsgService {

    private final UserMapper userMapper;
    private final SendmsgMapper sendmsgMapper;
    private final ReceivemsgMapper receivemsgMapper;

    public List<SendmsgDto> selectSendmsgList(Long senderId) throws Exception {
        List<SendmsgDto> sendmsgDtoList =  sendmsgMapper.selectSendmsgList(senderId);

        for (SendmsgDto sendmsgDto : sendmsgDtoList) {
            UserDto findUser = userMapper.findUserByUserId(sendmsgDto.getReceiverId())
                    .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
            sendmsgDto.setReceiverNickname(findUser.getUserNickname());
        }

        return sendmsgDtoList;
    }

    public SendmsgDto selectSendmsg(Long sendmsgId) throws Exception {
        // 읽음 표시
        SendmsgDto sendmsgDto = sendmsgMapper.selectSendmsg(sendmsgId)
                .orElseThrow(() -> new IllegalStateException(("잘못된 메세지 ID 입니다")));

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
    public void deleteSendmsg(Long sendmsgId, Long userId) throws Exception {
        int result = sendmsgMapper.deleteSendmsg(sendmsgId, userId);

        if (result == 0) {
            throw new IllegalStateException("본인이 보낸 메세지만 삭제 가능합니다");
        }
    }
}
