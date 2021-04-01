package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ReceivemsgDto {
    @ApiModelProperty(value = "메세지 ID (PK)")
    private Long receivemsgId;
    @ApiModelProperty(value = "내용")
    private String receivemsgContent;
    @ApiModelProperty(value = "작성일")
    private String receivemsgDate;
    @ApiModelProperty(value = "읽음 여부")
    private String receivemsgRead;
    @ApiModelProperty(value = "발신 유저 ID")
    private Long senderId;
    @ApiModelProperty(value = "발신 유저 닉네임")
    private String senderNickname;
    @ApiModelProperty(value = "수신 유저 ID")
    private Long receiverId;

    public ReceivemsgDto(String receivemsgContent, Long senderId, Long receiverId) {
        this.receivemsgContent = receivemsgContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
