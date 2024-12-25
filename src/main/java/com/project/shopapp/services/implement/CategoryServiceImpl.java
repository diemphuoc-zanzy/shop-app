package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.common.constant.MessageKeys.*;
import com.project.shopapp.dtos.response.CategoryResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.dtos.request.CategoryRequestDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.services.ICategoryService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.specs.CategorySpec;
import com.project.shopapp.utils.DtoMapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final CategorySpec categorySpec;

    protected final DtoMapperUtils dtoMapperUtils;

    @Override
    public Category iFindById(Long categoryId) {
        return categoryRepository
                .findOne(categorySpec.getCategoryById(categoryId))
                .orElseThrow(() -> new NotFoundException(this.message(CATEGORY.DETAIL_NOT_FOUND, categoryId)));
    }

    @Override
    public Category iFindByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    @Override
    public PaginatedDataResponse getCategories(CategoryRequestDto categoryRequestDto) {
        Page<Category> categories = categoryRepository
                .findAll(categorySpec.getCategories(categoryRequestDto), categoryRequestDto.toPageable());

        return dtoMapperUtils.makeResponse(CategoryResponseDto.class, categories);
    }

    @Override
    public PaginatedDataResponse getCategoryById(Long id) {
        if (id == null)
            throw new NotFoundException(this.message(CATEGORY.DETAIL_NOT_FOUND, id));

        Category category = this.iFindById(id);

        return dtoMapperUtils.makeResponse(CategoryResponseDto.class, category);
    }

    @Override
    public PaginatedDataResponse createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = this.iFindByName(categoryRequestDto.getName());
        if (category!= null) {
            category.updateRecordStatus(RECORD_STATUS.ACTIVE);
        } else {
            category = new Category(categoryRequestDto);
        }

        category = categoryRepository.save(category);
        return dtoMapperUtils.makeResponse(CategoryResponseDto.class, category);
    }

    @Override
    public PaginatedDataResponse updateCategory(CategoryRequestDto categoryRequestDto) {
        Category categoryExist = this.iFindById(categoryRequestDto.getId());

        categoryExist.update(categoryRequestDto);
        categoryExist = categoryRepository.save(categoryExist);

        return dtoMapperUtils.makeResponse(CategoryResponseDto.class, categoryExist);
    }

    @Override
    @Transactional
    public void deleteCategory(List<Long> categoryIds) {
        List<Category> categoryExists = categoryRepository.findAll(categorySpec.getCategoryByIds(categoryIds));
        categoryExists.forEach(categoryExist -> categoryExist.updateRecordStatus(RECORD_STATUS.DELETED));

        categoryRepository.saveAll(categoryExists);
    }
}
