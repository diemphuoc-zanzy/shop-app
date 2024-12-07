package com.project.shopapp.confiuration.exception;

public class ConflictException extends RuntimeException {
    /**
     * ConflictException (409 - Conflict)
     * Lỗi này thường xảy ra khi có sự xung đột, ví dụ như khi người dùng cố gắng tạo ra một tài nguyên mà đã tồn tại.
     */
    public ConflictException(String message) {
        super(message);
    }
}
