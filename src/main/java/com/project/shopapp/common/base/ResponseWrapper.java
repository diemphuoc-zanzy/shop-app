package com.project.shopapp.common.base;


import com.project.shopapp.common.RESPONSE_STATUS;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ResponseWrapper {
    private RESPONSE_STATUS status; // Trạng thái ("SUCCESS" hoặc "ERROR")
    private int messageCode;        // Mã thông điệp (mã lỗi hoặc mã thành công)
    private String message;         // Thông điệp (ví dụ: "OK" hoặc mô tả lỗi)
    private int errorCode;          // Mã lỗi (nếu có)
    private String errorMessage;    // Thông điệp lỗi (nếu có)

    public ResponseWrapper(){
        this.status = RESPONSE_STATUS.SUCCESS;
        this.message = HttpStatus.OK.name();
        this.messageCode = HttpStatus.OK.value();
        this.errorCode = 0;         // Không có lỗi khi thành công
        this.errorMessage = null;   // Không có thông điệp lỗi khi thành công
    }

    public ResponseWrapper(RESPONSE_STATUS status, int errorCode, String errorMessage) {
        this.status = status;
        this.messageCode = errorCode;
        this.message = errorMessage;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
