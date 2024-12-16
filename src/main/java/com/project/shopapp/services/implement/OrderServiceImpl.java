package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.response.OrderResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderRequestDto;
import com.project.shopapp.models.Order;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.services.IOrderService;
import com.project.shopapp.specs.OrderSpec;
import com.project.shopapp.utils.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderSpec orderSpec;
    private final DtoMapper mapper;

    @Override
    public Optional<Order> iFindOne(Specification<Order> specification) {
        return orderRepository.findOne(specification);
    }

    @Override
    public PaginatedDataResponse getOrders(OrderRequestDto orderRequestDto) {
        Page<Order> orders = orderRepository.findAll(orderSpec.getOrders(orderRequestDto), orderRequestDto.toPageable());
        return mapper.makeResponse(OrderResponseDto.class, orders);
    }

    @Override
    public PaginatedDataResponse createOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order(orderRequestDto);
        order = orderRepository.save(order);

        return mapper.makeResponse(OrderResponseDto.class, order);
    }

    @Override
    public PaginatedDataResponse updateOrder(OrderRequestDto orderRequestDto) {
        Order orderExist = orderRepository
                .findOne(orderSpec.getOrders(orderRequestDto))
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        orderExist.update(orderRequestDto);
        orderExist = orderRepository.save(orderExist);

        return mapper.makeResponse(OrderResponseDto.class, orderExist);
    }

    @Override
    @Transactional
    public void deleteOrder(List<Long> orderIds) {
        List<Order> orderExists = orderRepository.findAll(orderSpec.getOrderByIds(orderIds));
        orderExists.forEach(orderExist -> orderExist.updateRecordStatus(RECORD_STATUS.DELETED));

        orderRepository.saveAll(orderExists);
    }
}
