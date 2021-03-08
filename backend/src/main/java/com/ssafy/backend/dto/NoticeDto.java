package com.ssafy.backend.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private Long userId;
}
