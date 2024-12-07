package com.project.shopapp.confiuration.exception;

public class NotAcceptableException extends RuntimeException {
    /**
     * NotAcceptableException (406 - Not Acceptable)
     * Khi định dạng dữ liệu trả về không phù hợp với yêu cầu của client.
     */
    public NotAcceptableException(String message) {
        super(message);
    }
}
