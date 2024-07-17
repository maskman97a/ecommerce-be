package com.ecommerce.babystore.dto.response;

import com.ecommerce.babystore.common.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    int errorCode;
    String message;
    Object additionalInfo;

    public BaseResponse() {
        this.errorCode = ErrorType.Success;
        this.message = ErrorType.getMessage(errorCode);
    }

    public BaseResponse(int number, String message) {
        this.errorCode = number;
        this.message = message;
    }
}


