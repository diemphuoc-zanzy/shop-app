package com.project.shopapp.confiuration.exception;

public class DatabaseException extends RuntimeException {
    /**
     * DatabaseException (Lỗi cơ sở dữ liệu)
     * Đây là một ngoại lệ tùy chỉnh, ví dụ như khi có lỗi truy vấn cơ sở dữ liệu
     */
    public DatabaseException(String message) {
        super(message);
    }
}
