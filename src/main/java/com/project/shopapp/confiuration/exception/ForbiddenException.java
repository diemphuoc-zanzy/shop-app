package com.project.shopapp.confiuration.exception;

public class ForbiddenException extends RuntimeException {
    /**
     * ForbiddenException (403 - Forbidden)
     * Lỗi này xảy ra khi người dùng không có quyền truy cập vào tài nguyên dù đã xác thực.
     */
    public ForbiddenException(String message) {
        super(message);
    }
}
