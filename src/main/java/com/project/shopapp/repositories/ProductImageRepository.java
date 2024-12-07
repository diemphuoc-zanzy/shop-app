package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.ProductImage;

public interface ProductImageRepository extends BaseRepository<ProductImage, Long> {
    long countProductImageByProductId(Long productId);

}
