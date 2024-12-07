package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductImageService {

    PaginatedDataResponse uploadProductImage(Long productId, List<MultipartFile> files);
    void deleteProductImage(List<Long> productImageIds);
}
