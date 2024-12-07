package com.project.shopapp.confiuration.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDataException extends RuntimeException {
    private Object responseData;

    public ResponseDataException(String message) {
        super(message);
    }

    public ResponseDataException(String message, Object responseData) {
        super(message);
        this.responseData = responseData;
    }
}
