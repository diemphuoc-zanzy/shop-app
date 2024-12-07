package com.project.shopapp.dtos.response;

import com.project.shopapp.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {

    private Long userId;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String address;

    private String note;

    private Double totalMoney;

    private String shippingMethod;

    private String shippingAddress;

    private Instant shippingDate;

    private String trackingNumber;

    private String trackingMethod;

    public OrderResponseDto(Order order) {
        this.userId = order.getUserId();
        this.fullName = order.getFullName();
        this.phoneNumber = order.getPhoneNumber();
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.note = order.getNote();
        this.totalMoney = order.getTotalMoney();
        this.shippingMethod = order.getShippingMethod();
        this.shippingAddress = order.getShippingAddress();
        this.shippingDate = order.getShippingDate();
        this.trackingNumber = order.getTrackingNumber();
        this.trackingMethod = order.getTrackingMethod();
    }
}
