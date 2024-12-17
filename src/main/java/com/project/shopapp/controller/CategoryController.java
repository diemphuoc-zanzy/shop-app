package com.project.shopapp.controller;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.CategoryRequestDto;
import com.project.shopapp.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/gets")
    public ResponseEntity<PaginatedDataResponse> getCategories(@RequestBody CategoryRequestDto category) {
        PaginatedDataResponse response = categoryService.getCategories(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PaginatedDataResponse> getCategoryById(@PathVariable Long id) {
        PaginatedDataResponse response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PaginatedDataResponse> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        PaginatedDataResponse response = categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<PaginatedDataResponse> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        PaginatedDataResponse response = categoryService.updateCategory(categoryRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PaginatedDataResponse> deleteCategory(@RequestBody List<Long> categoryIds) {
        categoryService.deleteCategory(categoryIds);
        return ResponseEntity.ok(new PaginatedDataResponse("Delete category successfully"));
    }
}
