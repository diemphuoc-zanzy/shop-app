package com.project.shopapp.dtos.request;

import com.project.shopapp.dtos.request.base.BaseDto;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto extends BaseDto {

    @Min(value = 1, message = "User id must be greater than 0")
    private Long userId;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String address;

    private String note;

    @Min(value = 0, message = "Total money must be greater than 0")
    private Double totalMoney;

    private String shippingMethod;

    private String shippingAddress;

    private Instant shippingDate;

    private String trackingNumber;

    private String trackingMethod;
}
