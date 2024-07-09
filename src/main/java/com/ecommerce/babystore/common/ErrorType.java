package com.ecommerce.babystore.common;

import lombok.Data;

@Data
public class ErrorType {
    public static final int Success = 0;
    public static final int SystemError = 99;

    public static String getMessage(int errorCode) {
        switch (errorCode) {
            case Success -> {
                return "Thành công";
            }
            case SystemError -> {
                return "Lỗi hệ thống";
            }
        }
        return "Không xác định";
    }
}
