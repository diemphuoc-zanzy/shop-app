package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.common.constant.MessageKeys;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.dtos.response.OrderDetailResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderDetailRequestDto;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.services.IOrderDetailService;
import com.project.shopapp.services.IOrderService;
import com.project.shopapp.services.IProductService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.specs.OrderDetailSpec;
import com.project.shopapp.specs.OrderSpec;
import com.project.shopapp.specs.ProductSpec;
import com.project.shopapp.utils.DtoMapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl extends BaseServiceImpl implements IOrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final IOrderService orderService;
    private final IProductService productService;
    private final OrderDetailSpec orderDetailSpec;
    private final OrderSpec orderSpec;
    private final ProductSpec productSpec;

    private final DtoMapperUtils dtoMapperUtils;

    @Override
    public PaginatedDataResponse getDetails(OrderDetailRequestDto orderDetailDto) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll(orderDetailSpec.getOrderDetails(orderDetailDto));
        return dtoMapperUtils.makeResponse(OrderDetailResponseDto.class, orderDetails);
    }

    @Override
    public PaginatedDataResponse getOrderDetails(OrderDetailRequestDto orderDetailDto) {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll(orderDetailSpec.getOrderDetails(orderDetailDto));
        return dtoMapperUtils.makeResponse(OrderDetailResponseDto.class, orderDetails);
    }

    @Override
    @Transactional
    public PaginatedDataResponse createOrderDetail(OrderDetailRequestDto orderDetailDto) {
        if (orderDetailDto.getOrderId() == null) {
            throw new NotFoundException(this.message(MessageKeys.ORDER.DETAIL_NOT_FOUND));
        }

        Order order = orderService.iFindOne(orderSpec.getOrderById(orderDetailDto.getOrderId()))
                .orElseThrow(() -> new NotFoundException(this.message(MessageKeys.ORDER.DETAIL_NOT_FOUND)));

        Product product = productService.iFindOne(productSpec.getProductById(orderDetailDto.getProductId()))
                .orElseThrow(() -> new NotFoundException(this.message(MessageKeys.PRODUCT.DETAIL_NOT_FOUND)));

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
        return dtoMapperUtils.makeResponse(OrderDetailResponseDto.class, orderDetail);
    }

    @Override
    @Transactional
    public PaginatedDataResponse updateOrderDetail(OrderDetailRequestDto orderDetailDto) {
        if (orderDetailDto.getOrderId() == null) {
            throw new NotFoundException(this.message(MessageKeys.ORDER.DETAIL_NOT_FOUND));
        }

        Order order = orderService.iFindOne(orderSpec.getOrderById(orderDetailDto.getOrderId()))
                .orElseThrow(() -> new NotFoundException(this.message(MessageKeys.ORDER.DETAIL_NOT_FOUND)));

        Product product = productService.iFindOne(productSpec.getProductById(orderDetailDto.getProductId()))
                .orElseThrow(() -> new NotFoundException(this.message(MessageKeys.PRODUCT.DETAIL_NOT_FOUND)));

        OrderDetail orderDetailExist = orderDetailRepository
                .findOne(orderDetailSpec.getOrderDetailById(orderDetailDto.getId()))
                .orElseThrow(() -> new BadRequestException(this.message(MessageKeys.ORDER_DETAIL.DETAIL_NOT_FOUND)));

        orderDetailExist.update(orderDetailDto);
        orderDetailExist.setOrder(order);
        orderDetailExist.setProduct(product);
        orderDetailExist = orderDetailRepository.save(orderDetailExist);

        return dtoMapperUtils.makeResponse(OrderDetailResponseDto.class, orderDetailExist);
    }

    @Override
    public void deleteOrderDetails(List<Long> orderIds) {
        List<OrderDetail> orderDetailExists = orderDetailRepository.findAll(orderDetailSpec.getOrderDetailByIds(orderIds));
        orderDetailExists.forEach(orderExist -> orderExist.updateRecordStatus(RECORD_STATUS.DELETED));

        orderDetailRepository.saveAll(orderDetailExists);
    }
}
