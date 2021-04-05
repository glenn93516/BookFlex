package com.ssafy.backend.dto.highlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class HighlightDto {
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
    @ApiModelProperty(value = "문장수집 등록한 유저ID")
    private Long userId;
    @ApiModelProperty(value = "문장수집 등록한 유저닉네임")
    private String userNickname;
    @ApiModelProperty(value = "문장수집 등록한 유저 프로필 이미지 URL")
    private String userProfileImg;
    @ApiModelProperty(value = "문장 수집 생성 날짜")
    private String createdDate;
    @ApiModelProperty(value = "문장 수집 수정 날짜")
    private String updatedDate;
    @ApiModelProperty(value = "공개 여부 (1: 공개 / 0 : 비공개)")
    private int isPublic;

    public static HighlightDto createHighlight(HighlightRequestDto dto, Long userId) {
        HighlightDto highlightDto = new HighlightDto();
        highlightDto.userId = userId;
        highlightDto.highlightContent = dto.getHighlightContent();
        highlightDto.highlightPage = dto.getHighlightPage();
        highlightDto.bookIsbn = dto.getBookIsbn();
        highlightDto.isPublic = dto.getIsPublic();

        return highlightDto;
    }

    public void update(HighlightDto dto) {
        this.highlightContent = dto.getHighlightContent();
        this.highlightPage =  dto.getHighlightPage();
        this.isPublic = dto.getIsPublic();

        if (dto.getHighlightCover() != null) {
            this.highlightCover = dto.getHighlightCover();
        }
    }

    public int getIsPublic() {
        return this.isPublic;
    }
}
