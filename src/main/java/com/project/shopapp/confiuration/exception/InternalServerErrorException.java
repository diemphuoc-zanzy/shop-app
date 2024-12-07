package com.project.shopapp.confiuration.exception;

public class InternalServerErrorException extends RuntimeException {
    /**
     * InternalServerErrorException (500 - Internal Server Error)
     * Ngoại lệ này xảy ra khi có lỗi trên server mà không xác định được nguyên nhân cụ thể.
     */
    public InternalServerErrorException(String message) {
        super(message);
    }
}