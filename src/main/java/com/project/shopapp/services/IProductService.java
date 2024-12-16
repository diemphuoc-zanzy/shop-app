package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.ProductRequestDto;
import com.project.shopapp.models.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> iFindOne(Specification<Product> specification);
    PaginatedDataResponse getProducts(ProductRequestDto categoryDto);
    PaginatedDataResponse getProductById(Long id);
    PaginatedDataResponse createProduct(ProductRequestDto categoryDto);
    PaginatedDataResponse updateProduct(ProductRequestDto categoryDto);
    void deleteProducts(List<Long> id);
}
