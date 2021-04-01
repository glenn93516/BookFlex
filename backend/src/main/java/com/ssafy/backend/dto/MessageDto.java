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
public class MessageDto {
    @ApiModelProperty(value = "메세지 ID (PK)")
    private Long messageId;
    @ApiModelProperty(value = "제목")
    private String messageTitle;
    @ApiModelProperty(value = "내용")
    private String messageContent;
    @ApiModelProperty(value = "작성일")
    private String messageDate;
    @ApiModelProperty(value = "발신 유저 ID")
    private Long senderId;
    @ApiModelProperty(value = "수신 유저 ID")
    private Long receiverId;
    @ApiModelProperty(value = "읽음 여부")
    private int messageRead;
}
