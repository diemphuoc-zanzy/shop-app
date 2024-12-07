package com.project.shopapp.controller;


import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.services.IProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product_images")
public class ProductImageController {

    private final IProductImageService productImageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaginatedDataResponse> uploadProductImage(@RequestParam("productId") Long productId,
                                                                    @RequestParam("files") List<MultipartFile> files) {
        PaginatedDataResponse response = productImageService.uploadProductImage(productId, files);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<PaginatedDataResponse> deleteProductImage(@RequestBody List<Long> productImageIds) {
        productImageService.deleteProductImage(productImageIds);
        return ResponseEntity.ok(new PaginatedDataResponse("Delete product image successfully"));
    }

}
