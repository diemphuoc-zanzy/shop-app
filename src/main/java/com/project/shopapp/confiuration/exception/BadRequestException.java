package com.project.shopapp.confiuration.exception;


public class BadRequestException extends RuntimeException {
    /**
     * BadRequestException (400 - Bad Request)
     * Lỗi này thường xảy ra khi dữ liệu đầu vào không hợp lệ hoặc thiếu thông tin.
     */
    public BadRequestException(String message) {
        super(message);
    }

}


