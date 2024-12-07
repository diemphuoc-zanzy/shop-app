package com.project.shopapp.confiuration.exception;

public class MethodNotAllowedException extends RuntimeException {
    /**
     * MethodNotAllowedException (405 - Method Not Allowed)
     * Ngoại lệ này được ném khi phương thức HTTP không được phép cho một URL nhất định.
     */
    public MethodNotAllowedException(String message) {
        super(message);
    }
}
