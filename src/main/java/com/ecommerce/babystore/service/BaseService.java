package com.ecommerce.babystore.service;

import com.ecommerce.babystore.common.ErrorType;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected int validateSignature(String data, String signature) {
        return ErrorType.Success;
    }
}
