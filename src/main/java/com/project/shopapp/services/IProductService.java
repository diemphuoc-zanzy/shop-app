package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.ProductRequestDto;

import java.util.List;

public interface IProductService {
    PaginatedDataResponse getProducts(ProductRequestDto categoryDto);
    PaginatedDataResponse getProductById(Long id);
    PaginatedDataResponse createProduct(ProductRequestDto categoryDto);
    PaginatedDataResponse updateProduct(ProductRequestDto categoryDto);
    void deleteProducts(List<Long> id);
}
