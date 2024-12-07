package com.project.shopapp.models;

import com.project.shopapp.common.ORDER_STATUS;
import com.project.shopapp.dtos.request.OrderRequestDto;
import com.project.shopapp.models.base.BaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "fullname", length = 100)
    private String fullName;

    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    private String note;

    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @Enumerated(EnumType.STRING)
    private ORDER_STATUS status;

    @Column(nullable = false)
    @Min(value = 0, message = "total money is greater than 0")
    private Double totalMoney;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_date")
    private Instant shippingDate;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "tracking_method")
    private String trackingMethod;


    // tạo mới Order
    public Order(OrderRequestDto orderRequestDto) {
        this.userId = orderRequestDto.getUserId();
        this.fullName = orderRequestDto.getFullName();
        this.email = orderRequestDto.getEmail();
        this.phoneNumber = orderRequestDto.getPhoneNumber();
        this.address = orderRequestDto.getAddress();
        this.note = orderRequestDto.getNote();
        this.orderDate = orderRequestDto.getCreatedAt();
        this.status = ORDER_STATUS.PENDING;
        this.totalMoney = orderRequestDto.getTotalMoney();
        this.shippingMethod = orderRequestDto.getShippingMethod();
        this.shippingAddress = orderRequestDto.getShippingAddress();
        this.shippingDate = orderRequestDto.getShippingDate();
        this.trackingNumber = orderRequestDto.getTrackingNumber();
        this.trackingMethod = orderRequestDto.getTrackingMethod();
    }

    public void update(OrderRequestDto orderRequestDto) {
        this.fullName = orderRequestDto.getFullName();
        this.email = orderRequestDto.getEmail();
        this.phoneNumber = orderRequestDto.getPhoneNumber();
        this.address = orderRequestDto.getAddress();
        this.note = orderRequestDto.getNote();
        this.totalMoney = orderRequestDto.getTotalMoney();
        this.shippingMethod = orderRequestDto.getShippingMethod();
        this.shippingAddress = orderRequestDto.getShippingAddress();
        this.shippingDate = orderRequestDto.getShippingDate();
        this.trackingNumber = orderRequestDto.getTrackingNumber();
        this.trackingMethod = orderRequestDto.getTrackingMethod();
    }
}
