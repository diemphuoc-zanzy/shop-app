package com.project.shopapp.controller;

import com.project.shopapp.dtos.request.OrderDetailRequestDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.services.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @PostMapping("/detail")
    public ResponseEntity<PaginatedDataResponse> getDetails(@RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        PaginatedDataResponse response = orderDetailService.getDetails(orderDetailRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/order")
    public ResponseEntity<PaginatedDataResponse> getOrderDetails(@RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        PaginatedDataResponse response = orderDetailService.getOrderDetails(orderDetailRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<PaginatedDataResponse> createDetail(@RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        PaginatedDataResponse response = orderDetailService.createOrderDetail(orderDetailRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<PaginatedDataResponse> updateDetail(@RequestBody OrderDetailRequestDto orderDetailRequestDto) {
        PaginatedDataResponse response = orderDetailService.updateOrderDetail(orderDetailRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<PaginatedDataResponse> deleteDetail(@RequestBody List<Long> orderDetailIds) {
        orderDetailService.deleteOrderDetails(orderDetailIds);
        return ResponseEntity.ok(new PaginatedDataResponse("Delete order detail successfully"));
    }
}
