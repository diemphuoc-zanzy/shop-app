package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends BaseRepository<Category, Long> {

    Category findByName(String name);
}
