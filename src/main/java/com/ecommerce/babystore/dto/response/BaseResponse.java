package com.ecommerce.babystore.dto.response;

import com.ecommerce.babystore.common.ErrorType;
import lombok.Data;

@Data
public class BaseResponse {
    int errorCode;
    String message;
    Object additionalInfo;

    public BaseResponse() {
        this.errorCode = ErrorType.Success;
        this.message = ErrorType.getMessage(errorCode);
    }
}