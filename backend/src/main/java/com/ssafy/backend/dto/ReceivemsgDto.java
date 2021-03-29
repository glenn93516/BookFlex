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
    @ApiModelProperty(value = "제목")
    private String receivemsgTitle;
    @ApiModelProperty(value = "내용")
    private String receivemsgContent;
    @ApiModelProperty(value = "작성일")
    private String receivemsgDate;
    @ApiModelProperty(value = "발신 유저 ID")
    private Long senderId;
    @ApiModelProperty(value = "수신 유저 ID")
    private Long receiverId;

    public ReceivemsgDto(String receivemsgTitle, String receivemsgContent, Long senderId, Long receiverId) {
        this.receivemsgTitle = receivemsgTitle;
        this.receivemsgContent = receivemsgContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
