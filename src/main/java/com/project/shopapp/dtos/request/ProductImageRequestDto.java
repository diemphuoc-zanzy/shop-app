package com.project.shopapp.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageRequestDto {

    @Min(value = 1, message = "Product's must be greater than 0")
    private Long productId;

    @Size(min = 5, max = 200, message = "Name must be between 5 and 200 characters")
    private String imageUrl;
}
