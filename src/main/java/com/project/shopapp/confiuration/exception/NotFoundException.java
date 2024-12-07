package com.project.shopapp.confiuration.exception;

public class NotFoundException extends RuntimeException {
    /**
     * NotFoundException (404 - Not Found)
     * Ngoại lệ này dùng khi tài nguyên yêu cầu không được tìm thấy.
     */
    public NotFoundException(String message) {
        super(message);
    }
}