package com.project.shopapp.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.shopapp.models.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDto{
    private Long orderId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResponseDto order;

    private ProductResponseDto product;

    private Double price;

    private Integer numberOfProducts;

    private Double totalMoney;

    private String color;

    public OrderDetailResponseDto(OrderDetail orderDetail) {
        this.orderId = orderDetail.getOrder().getId();
        this.price = orderDetail.getPrice();
        this.numberOfProducts = orderDetail.getNumberOfProducts();
        this.totalMoney = orderDetail.getTotalMoney();
        this.color = orderDetail.getColor();

        if (orderDetail.getOrder() != null) {
            this.order = new OrderResponseDto(orderDetail.getOrder());
        }
        if (orderDetail.getProduct()!= null) {
            this.product = new ProductResponseDto(orderDetail.getProduct());
        }
    }
}
