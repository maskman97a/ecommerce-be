package com.ecommerce.babystore.dto.request;

import lombok.Data;

@Data
public class BaseRequest<T> {
    T data;
    String signature;
}
