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
    @ApiModelProperty(value = "책 isbn")
    private Long bookIsbn;
}
