package com.project.shopapp.dtos.request;

import com.project.shopapp.dtos.request.base.BaseDto;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequestDto extends BaseDto {
    @Min(value = 1, message = "Order id must be greater than 0")
    private Long id;

    @Min(value = 1, message = "Order id must be greater than 0")
    private Long orderId;

    @Min(value = 1, message = "Product id must be greater than 0")
    private Long productId;

    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;

    @Min(value = 1, message = "Number of products must be greater than 1")
    private Integer numberOfProducts;

    @Min(value = 1, message = "Total money must be greater than 1")
    private Double totalMoney;

    private String color;
}
