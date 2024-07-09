package com.ecommerce.babystore.service;

import com.ecommerce.babystore.common.ErrorType;
import com.ecommerce.babystore.dto.request.BaseRequest;
import com.ecommerce.babystore.dto.request.TestRequest;
import com.ecommerce.babystore.dto.response.BaseResponse;
import com.ecommerce.babystore.dto.response.TestResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class TestService extends BaseService {
    public BaseResponse doTest(BaseRequest baseRequest) {
        TestRequest request = (TestRequest) baseRequest.getData();
        String requestStr = new Gson().toJson(request);
        TestResponse response = new TestResponse();
        int errorCode = validateSignature(requestStr, baseRequest.getSignature());
        if (errorCode == ErrorType.Success) {
            try {
                if (response.getErrorCode() == ErrorType.Success) {
                    //Xu ly logic tai day
                    return response;
                } else {
                    return response;
                }
            } catch (Exception ex) {
                response.setErrorCode(ErrorType.SystemError);
                response.setMessage(ErrorType.getMessage(response.getErrorCode()));
            }
        } else {
            response.setErrorCode(errorCode);
            response.setMessage(ErrorType.getMessage(errorCode));
        }
        return response;
    }
}
