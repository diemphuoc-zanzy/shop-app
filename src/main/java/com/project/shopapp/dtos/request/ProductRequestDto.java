package com.project.shopapp.dtos.request;

import com.project.shopapp.dtos.request.base.BaseDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto extends BaseDto {

    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 200, message = "name must be between 5 and 100 characters")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be greater than or equal to 0")
    @Max(value = 10000000, message = "price must be less than or equal to 10,000,000")
    private Double price;

    private String thumbnail;

    private String description;

    private String categoryId;

    private List<MultipartFile> files = new ArrayList<>();

    private String categoryName;

}
