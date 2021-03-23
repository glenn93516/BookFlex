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
public class UserBookDto {
    @ApiModelProperty(value = "유저-책 ID (PK)")
    private Long userBookId;
    @ApiModelProperty(value = "유저 ID")
    private Long userId;
    @ApiModelProperty(value = "책 isbn (읽은 책 등록시 이 값만 넣어주면 됨)")
    private Long bookIsbn;
}
