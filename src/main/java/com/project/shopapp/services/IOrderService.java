package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderRequestDto;
import com.project.shopapp.models.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Optional<Order> iFindOne(Specification<Order> specification);
    PaginatedDataResponse getOrders(OrderRequestDto orderRequestDto);
    PaginatedDataResponse createOrder(OrderRequestDto orderRequestDto);
    PaginatedDataResponse updateOrder(OrderRequestDto orderRequestDto);
    void deleteOrder(List<Long> orderIds);

}
