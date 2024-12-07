package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.Product;

public interface ProductRepository extends BaseRepository<Product, Long> {

    boolean existsByName(String name);

    Product findByName(String name);
}
