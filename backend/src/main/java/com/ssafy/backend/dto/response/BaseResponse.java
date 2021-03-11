package com.ssafy.backend.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BaseResponse {
    @ApiModelProperty(value = "요청 성공 여부")
    private boolean success; // 요청 성공 여부
    @ApiModelProperty(value = "응답 메세지")
    private String message; // 응답 메세지
}
