package com.project.shopapp.confiuration.exception;

public class TimeoutException extends RuntimeException {
    /**
     * TimeoutException (408 - Request Timeout)
     * Ngoại lệ này xảy ra khi client không thể hoàn thành yêu cầu trong thời gian quy định.
     */
    public TimeoutException(String message) {
        super(message);
    }
}