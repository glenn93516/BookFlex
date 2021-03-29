package com.ssafy.backend.dto.highlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "문장 수집 등록용 DTO")
public class HighlightRequestDto {

    @ApiModelProperty(value = "문장 수집 내용")
    private String highlightContent;
    @ApiModelProperty(value = "책 페이지")
    private int highlightPage;
    @ApiModelProperty(value = "배경 이미지 파일")
    private MultipartFile highlightCover;
    @ApiModelProperty(value = "문장수집 등록할 책 ISBN")
    private Long bookIsbn;
    @ApiModelProperty(value = "공개 여부 (1: 공개 / 0 : 비공개)")
    private int isPublic;

    public int getIsPublic() {
        return this.isPublic;
    }

    @Override
    public String toString() {
        return "HighlightRequestDto{" +
                "highlightContent='" + highlightContent + '\'' +
                ", highlightPage=" + highlightPage +
                ", highlightCover=" + highlightCover +
                ", bookIsbn=" + bookIsbn +
                ", isPublic=" + isPublic +
                '}';
    }
}
