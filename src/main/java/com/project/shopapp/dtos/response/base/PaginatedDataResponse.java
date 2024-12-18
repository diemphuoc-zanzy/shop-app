package com.project.shopapp.dtos.response.base;

import com.project.shopapp.common.RESPONSE_STATUS;
import com.project.shopapp.common.base.PaginationDetails;
import com.project.shopapp.common.base.ResponseWrapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginatedDataResponse extends ResponseWrapper {
    private Object responseData;  // Dữ liệu trả về (nếu có)
    private PaginationDetails pagination;    // Thông tin meta (ví dụ: phân trang)

    // Constructor cho thành công
    public PaginatedDataResponse(Object responseData) {
        super();
        this.responseData = responseData;
        this.pagination = null;
    }

    // Constructor cho thành oông custom
    public PaginatedDataResponse(Object responseData, PaginationDetails paginationDetails) {
        super();
        this.responseData = responseData;
        this.pagination = paginationDetails;
    }

    // Constructor cho lỗi
    public PaginatedDataResponse(RESPONSE_STATUS status, int errorCode, String errorMessage) {
        super(status, errorCode, errorMessage);
        this.responseData = null;   // Không có dữ liệu khi lỗi
        this.pagination = null;   // Không cần meta trong lỗi
    }

    // Constructor cho lỗi custom
    public PaginatedDataResponse(RESPONSE_STATUS status, int errorCode, String errorMessage, Object responseData) {
        super(status, errorCode, errorMessage);
        this.responseData = responseData;   // Dữ liệu định dạng khi lỗi
        this.pagination = null;   // Không cần meta trong lỗi
    }
}
