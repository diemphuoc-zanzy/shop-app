package com.project.shopapp.confiuration.exception;

import com.project.shopapp.common.RESPONSE_STATUS;
import com.project.shopapp.common.base.ResponseWrapper;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý phương thức HTTP không được hỗ trợ cho URL hiện tại (405 - Method Not Allowed)
    // (ví dụ: sử dụng GET thay vì POST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<PaginatedDataResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<PaginatedDataResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // Xử lý IllegalArgumentException (400 Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<PaginatedDataResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Xử lý MethodArgumentNotValidException (400 Bad Request) cho validation lỗi
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<PaginatedDataResponse> handleValidationException(MethodArgumentNotValidException ex) {

        List<ResponseWrapper> errorList = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ResponseWrapper(
                        RESPONSE_STATUS.ERROR,
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        error.getDefaultMessage()
                        ))
                .toList();

        String message = "Validation exception";
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.BAD_REQUEST.value(),
                message,
                errorList
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Xử lý NullPointerException (500 Internal Server Error)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<PaginatedDataResponse> handleNullPointerException(NullPointerException ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseDataException.class)
    public ResponseEntity<PaginatedDataResponse> handleResponseDataException(ResponseDataException ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                ex.getMessage(),
                ex.getResponseData()
        );

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // Xử lý tất cả các ngoại lệ còn lại (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<PaginatedDataResponse> handleGenericException(Exception ex) {
        PaginatedDataResponse response = new PaginatedDataResponse(
                RESPONSE_STATUS.ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
