package com.project.shopapp.controller;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.OrderRequestDto;
import com.project.shopapp.services.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/gets")
    public ResponseEntity<PaginatedDataResponse> getOrders(@RequestBody OrderRequestDto orderRequestDto) {
        PaginatedDataResponse response = orderService.getOrders(orderRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PaginatedDataResponse> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        PaginatedDataResponse response = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @PutMapping("/update")
    public ResponseEntity<PaginatedDataResponse> updateOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        PaginatedDataResponse response = orderService.updateOrder(orderRequestDto);
        return ResponseEntity.ok().body(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<PaginatedDataResponse> deleteOrder(@RequestBody List<Long> orderIds) {
        orderService.deleteOrder(orderIds);
        return ResponseEntity.ok(new PaginatedDataResponse("Delete products successfully"));
    }

}
