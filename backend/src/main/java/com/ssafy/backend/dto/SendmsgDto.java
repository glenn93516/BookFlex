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
public class SendmsgDto {
    @ApiModelProperty(value = "메세지 ID (PK)")
    private Long sendmsgId;
    @ApiModelProperty(value = "내용")
    private String sendmsgContent;
    @ApiModelProperty(value = "작성일")
    private String sendmsgDate;
    @ApiModelProperty(value = "발신 유저 ID")
    private Long senderId;
    @ApiModelProperty(value = "수신 유저 ID")
    private Long receiverId;
    @ApiModelProperty(value = "수신 유저 닉네임")
    private String receiverNickname;
}
