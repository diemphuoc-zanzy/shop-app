package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderRequestDto;

import java.util.List;

public interface IOrderService {
    PaginatedDataResponse getOrders(OrderRequestDto orderRequestDto);
    PaginatedDataResponse createOrder(OrderRequestDto orderRequestDto);
    PaginatedDataResponse updateOrder(OrderRequestDto orderRequestDto);
    void deleteOrder(List<Long> orderIds);

}
