package com.project.shopapp.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.shopapp.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;

    private String name;

    private Double price;

    private String thumbnail;

    private String description;

    private Long categoryId;

    private String categoryName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MultipartFile> files;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.thumbnail = product.getThumbnail();
        this.description = product.getDescription();
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
        }
    }
}
