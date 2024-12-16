package com.project.shopapp.common.base;


import com.project.shopapp.common.RESPONSE_STATUS;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ResponseWrapper {
    private RESPONSE_STATUS status; // ("SUCCESS" || "ERROR")
    private int messageCode;        // message code (example: 200)
    private String message;         // message (example: "OK" or message error)
    private int errorCode;          // error code
    private String errorMessage;    // message error

    public ResponseWrapper(){
        this.status = RESPONSE_STATUS.SUCCESS;
        this.message = HttpStatus.OK.name();
        this.messageCode = HttpStatus.OK.value();
        this.errorCode = 0;
        this.errorMessage = null;
    }

    public ResponseWrapper(RESPONSE_STATUS status, int errorCode, String errorMessage) {
        this.status = status;
        this.messageCode = errorCode;
        this.message = errorMessage;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
