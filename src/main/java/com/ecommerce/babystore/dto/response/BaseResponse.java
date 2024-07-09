package com.ecommerce.babystore.dto.response;

import com.ecommerce.babystore.common.ErrorType;
import lombok.Data;

@Data
public class BaseResponse {
    int errorCode;
    String message;

    public BaseResponse() {
        this.errorCode = ErrorType.Success;
        this.message = ErrorType.getMessage(errorCode);
    }
}
