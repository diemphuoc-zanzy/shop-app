package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderDetailRequestDto;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.IOrderDetailService;
import com.project.shopapp.specs.OrderDetailSpec;
import com.project.shopapp.specs.OrderSpec;
import com.project.shopapp.specs.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderDetailSpec orderDetailSpec;
    private final OrderSpec orderSpec;
    private final ProductSpec productSpec;

    @Override
    public PaginatedDataResponse getDetails(OrderDetailRequestDto orderDetailDto) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll(orderDetailSpec.getOrderDetails(orderDetailDto));
        return new PaginatedDataResponse(orderDetails);
    }

    @Override
    public PaginatedDataResponse getOrderDetails(OrderDetailRequestDto orderDetailDto) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll(orderDetailSpec.getOrderDetails(orderDetailDto));
        return new PaginatedDataResponse(orderDetails);
    }

    @Override
    @Transactional
    public PaginatedDataResponse createOrderDetail(OrderDetailRequestDto orderDetailDto) {
        if (orderDetailDto.getOrderId() == null) {
            throw new NotFoundException("Order Is Not Found");
        }

        Order order = orderRepository.findOne(orderSpec.getOrderById(orderDetailDto.getOrderId()))
                .orElseThrow(() -> new NotFoundException("Order Is Not Found"));

        Product product = productRepository.findOne(productSpec.getProductById(orderDetailDto.getProductId()))
                .orElseThrow(() -> new NotFoundException("Product Is Not Found"));

        OrderDetail orderDetail = orderDetailRepository
                .findOne(orderDetailSpec.getOrderDetailById(orderDetailDto.getId()))
                .orElse(new OrderDetail(orderDetailDto));

        if (orderDetail.getId() != null) {
            orderDetail.updateRecordStatus(RECORD_STATUS.ACTIVE);
            orderDetail.update(orderDetailDto);
        }

        orderDetail.setOrder(order);
        orderDetail.setProduct(product);

        orderDetail = orderDetailRepository.save(orderDetail);
        return new PaginatedDataResponse(orderDetail);
    }

    @Override
    @Transactional
    public PaginatedDataResponse updateOrderDetail(OrderDetailRequestDto orderDetailDto) {
        if (orderDetailDto.getOrderId() == null) {
            throw new NotFoundException("Order Is Not Found");
        }

        Order order = orderRepository.findOne(orderSpec.getOrderById(orderDetailDto.getOrderId()))
                .orElseThrow(() -> new NotFoundException("Order Is Not Found"));

        Product product = productRepository.findOne(productSpec.getProductById(orderDetailDto.getProductId()))
                .orElseThrow(() -> new NotFoundException("Product Is Not Found"));

        OrderDetail orderDetailExist = orderDetailRepository
                .findOne(orderDetailSpec.getOrderDetailById(orderDetailDto.getId()))
                .orElseThrow(() -> new BadRequestException("Not found OrderDetail"));

        orderDetailExist.update(orderDetailDto);
        orderDetailExist.setOrder(order);
        orderDetailExist.setProduct(product);
        orderDetailExist = orderDetailRepository.save(orderDetailExist);

        return new PaginatedDataResponse(orderDetailExist);
    }

    @Override
    public void deleteOrderDetails(List<Long> orderIds) {
        List<OrderDetail> orderDetailExists = orderDetailRepository.findAll(orderDetailSpec.getOrderDetailByIds(orderIds));
        orderDetailExists.forEach(orderExist -> orderExist.updateRecordStatus(RECORD_STATUS.DELETED));

        orderDetailRepository.saveAll(orderDetailExists);
    }
}
