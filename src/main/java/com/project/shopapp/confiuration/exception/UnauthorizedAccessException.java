package com.project.shopapp.confiuration.exception;

public class UnauthorizedAccessException extends RuntimeException {
    /**
     * UnauthorizedAccessException (403 - Forbidden)
     * Được sử dụng khi người dùng đã xác thực nhưng không có quyền truy cập vào tài nguyên cụ thể.
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
