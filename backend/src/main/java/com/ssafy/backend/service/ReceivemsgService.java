package com.ssafy.backend.service;

import com.ssafy.backend.dto.ReceivemsgDto;
import com.ssafy.backend.dto.UserDto;
import com.ssafy.backend.exception.UserNotFoundException;
import com.ssafy.backend.mapper.ReceivemsgMapper;
import com.ssafy.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceivemsgService {

    private final UserMapper userMapper;
    private final ReceivemsgMapper receivemsgMapper;

    @Transactional
    public List<ReceivemsgDto> selectReceivemsgList(Long receiverId) throws Exception {
        List<ReceivemsgDto> receivemsgDtoList = receivemsgMapper.selectReceivemsgList(receiverId);

        for (ReceivemsgDto receivemsgDto : receivemsgDtoList) {
            UserDto findUser = userMapper.findUserByUserId(receivemsgDto.getSenderId())
                    .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
            receivemsgDto.setSenderNickname(findUser.getUserNickname());
        }

        return receivemsgDtoList;
    }

    @Transactional
    public ReceivemsgDto selectReceivemsg(Long receivemsgId) throws Exception {
        receivemsgMapper.updateReceivemsg(receivemsgId); // 읽음 표시

        ReceivemsgDto receivemsgDto = receivemsgMapper.selectReceivemsg(receivemsgId);

        UserDto findUser = userMapper.findUserByUserId(receivemsgDto.getSenderId())
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
        receivemsgDto.setSenderNickname(findUser.getUserNickname());

        return receivemsgDto;
    }

    @Transactional
    public int deleteReceivemsg(Long receivemsgId) throws Exception {
        return receivemsgMapper.deleteReceivemsg(receivemsgId);
    }
}
