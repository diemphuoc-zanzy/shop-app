package com.project.shopapp.confiuration.exception;

public class TooManyRequestsException extends RuntimeException {
    /**
     * TooManyRequestsException (429 - Too Many Requests)
     * Ngoại lệ này xảy ra khi client gửi quá nhiều yêu cầu trong một khoảng thời gian ngắn.
     */
    public TooManyRequestsException(String message) {
        super(message);
    }
}
