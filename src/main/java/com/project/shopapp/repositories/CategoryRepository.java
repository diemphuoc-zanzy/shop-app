package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.Category;

public interface CategoryRepository extends BaseRepository<Category, Long> {

    Category findByName(String name);
}
