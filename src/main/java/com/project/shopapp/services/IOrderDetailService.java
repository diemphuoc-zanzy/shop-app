package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderDetailRequestDto;

import java.util.List;

public interface IOrderDetailService {
    PaginatedDataResponse getDetails(OrderDetailRequestDto orderDetailRequestDto);
    PaginatedDataResponse getOrderDetails(OrderDetailRequestDto orderDto);
    PaginatedDataResponse createOrderDetail(OrderDetailRequestDto orderDto);
    PaginatedDataResponse updateOrderDetail(OrderDetailRequestDto orderDto);
    void deleteOrderDetails(List<Long> orderId);
}
