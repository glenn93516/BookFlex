package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class GenreDto {
    @ApiModelProperty(value = "장르 아이디(PK)")
    private Long genreId;
    @ApiModelProperty(value = "장르 이름")
    private String genreName;
}
