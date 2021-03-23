package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class LoginDto {
    @ApiModelProperty(value = "이메일")
    private String userEmail;
    @ApiModelProperty(value = "비밀번호")
    private String userPassword;
}
