package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.CategoryRequestDto;

import java.util.List;

public interface ICategoryService {
    PaginatedDataResponse getCategories(CategoryRequestDto categoryRequestDto);
    PaginatedDataResponse getCategoryById(Long id);
    PaginatedDataResponse createCategory(CategoryRequestDto categoryRequestDto);
    PaginatedDataResponse updateCategory(CategoryRequestDto categoryRequestDto);
    void deleteCategory(List<Long> id);
}
