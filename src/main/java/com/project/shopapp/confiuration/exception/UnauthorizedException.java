package com.project.shopapp.confiuration.exception;

public class UnauthorizedException extends RuntimeException {
    /**
     * UnauthorizedException (401 - Unauthorized)
     * Ngoại lệ này thường được sử dụng khi người dùng không có quyền truy cập vào tài nguyên.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
