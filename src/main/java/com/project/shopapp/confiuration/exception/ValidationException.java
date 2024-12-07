package com.project.shopapp.confiuration.exception;

public class ValidationException extends RuntimeException {
    /**
     * ValidationException (422 - Unprocessable Entity)
     * Ngoại lệ này được sử dụng khi dữ liệu đầu vào không hợp lệ theo yêu cầu của hệ thống (ví dụ, validation failed).
     */
    public ValidationException(String message) {
        super(message);
    }
}