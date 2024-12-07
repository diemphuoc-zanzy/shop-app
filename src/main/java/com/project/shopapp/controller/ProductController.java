package com.project.shopapp.controller;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.ProductRequestDto;
import com.project.shopapp.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping("/gets")
    public ResponseEntity<PaginatedDataResponse> gets(@RequestBody ProductRequestDto productRequestDto) {
        PaginatedDataResponse response = productService.getProducts(productRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PaginatedDataResponse> getProductById(@PathVariable Long id) {
        PaginatedDataResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PaginatedDataResponse> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        PaginatedDataResponse response = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<PaginatedDataResponse> updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        PaginatedDataResponse response = productService.updateProduct(productRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PaginatedDataResponse> deleteProducts(@RequestBody List<Long> productIds) {
        productService.deleteProducts(productIds);
        return ResponseEntity.ok(new PaginatedDataResponse("Delete product successfully"));
    }

}

