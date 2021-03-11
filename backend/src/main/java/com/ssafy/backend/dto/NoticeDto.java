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
public class NoticeDto {
    @ApiModelProperty(value = "공지사항 ID (PK)")
    private Long noticeId;
    @ApiModelProperty(value = "제목")
    private String noticeTitle;
    @ApiModelProperty(value = "내용")
    private String noticeContent;
    @ApiModelProperty(value = "공지사항 작성일")
    private String noticeDate;
    @ApiModelProperty(value = "작성한 유저 ID")
    private Long userId;
}
