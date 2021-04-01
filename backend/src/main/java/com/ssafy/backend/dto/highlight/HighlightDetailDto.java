package com.ssafy.backend.dto.highlight;

import com.ssafy.backend.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "문장수집 상세보기 DTO(등록한 유저 상세정보 들어있음)")
public class HighlightDetailDto {
    @ApiModelProperty(value = "문장 수집 ID(PK)")
    private Long highlightId;
    @ApiModelProperty(value = "문장 수집 내용")
    private String highlightContent;
    @ApiModelProperty(value = "책 페이지")
    private int highlightPage;
    @ApiModelProperty(value = "배경 이미지 파일 url")
    private String highlightCover;
    @ApiModelProperty(value = "문장수집 등록할 책 ISBN")
    private Long bookIsbn;
    @ApiModelProperty(value = "문장수집 등록할 책 제목")
    private String bookTitle;
    @ApiModelProperty(value = "문장수집 등록한 유저")
    private UserDto user;
    @ApiModelProperty(value = "문장 수집 생성 날짜")
    private String createdDate;
    @ApiModelProperty(value = "문장 수집 수정 날짜")
    private String updatedDate;
    @ApiModelProperty(value = "공개 여부 (1: 공개 / 0 : 비공개)")
    private int isPublic;
    @ApiModelProperty(value = "좋아요 개수")
    private int goodCount;
    @ApiModelProperty(value = "해당 유저가 좋아요 눌렀는지 여부 (로그인 안한 경우 false)")
    private boolean userGood;

    public int getIsPublic() {
        return this.isPublic;
    }

    public boolean getUserGood() {
        return this.userGood;
    }
}
