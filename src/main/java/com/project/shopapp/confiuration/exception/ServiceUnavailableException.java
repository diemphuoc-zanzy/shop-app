package com.project.shopapp.confiuration.exception;

public class ServiceUnavailableException extends RuntimeException {
    /**
     * ServiceUnavailableException (503 - Service Unavailable)
     * Lỗi này xảy ra khi dịch vụ không sẵn sàng (ví dụ, server quá tải hoặc bảo trì).
     */
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
