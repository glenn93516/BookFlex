package com.ssafy.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleDataResponse<T> extends BaseResponse {
    private T data; // 전달할 데이터
}
