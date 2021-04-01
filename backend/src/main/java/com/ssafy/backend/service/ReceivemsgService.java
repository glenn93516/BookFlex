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
@Transactional(readOnly = true)
public class ReceivemsgService {

    private final UserMapper userMapper;
    private final ReceivemsgMapper receivemsgMapper;

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
        // 읽음 표시
        receivemsgMapper.updateReceivemsg(receivemsgId);

        ReceivemsgDto receivemsgDto = receivemsgMapper.selectReceivemsg(receivemsgId)
                .orElseThrow(() -> new IllegalStateException("잘못된 메세지 ID 입니다."));

        UserDto findUser = userMapper.findUserByUserId(receivemsgDto.getSenderId())
                .orElseThrow(() -> new UserNotFoundException("없는 유저입니다."));
        receivemsgDto.setSenderNickname(findUser.getUserNickname());

        return receivemsgDto;
    }

    @Transactional
    public void deleteReceivemsg(Long receivemsgId, Long userId) throws Exception {
        int result = receivemsgMapper.deleteReceivemsg(receivemsgId, userId);

        if (result == 0) {
            throw new IllegalStateException("본인에게 온 메세지만 삭제 가능합니다.");
        }
    }
}
