package com.ssafy.backend.service;

import com.ssafy.backend.dto.response.BaseResponse;
import com.ssafy.backend.dto.response.ListDataResponse;
import com.ssafy.backend.dto.response.SingleDataResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleDataResponse<T> getSingleDataResponse(boolean success, String message, T data) {
        SingleDataResponse<T> response = new SingleDataResponse<>();
        response.setSuccess(success);
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public <T> ListDataResponse<T> getListDataResponse(boolean success, String message, List<T> data) {
        ListDataResponse<T> response = new ListDataResponse<>();
        response.setSuccess(success);
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public BaseResponse getBaseResponse(boolean success, String message) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(success);
        response.setMessage(message);

        return response;
    }
}
