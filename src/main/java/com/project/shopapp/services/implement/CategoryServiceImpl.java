package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.response.CategoryResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.dtos.request.CategoryRequestDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.repositories.CategoryRepository;
import com.project.shopapp.services.ICategoryService;
import com.project.shopapp.specs.CategorySpec;
import com.project.shopapp.utils.DtoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final CategorySpec categorySpec;

    private final DtoMapper dtoMapper;

    @Override
    public PaginatedDataResponse getCategories(CategoryRequestDto categoryRequestDto) {
        Page<Category> categories = categoryRepository
                .findAll(categorySpec.getCategories(categoryRequestDto), categoryRequestDto.toPageable());

        return dtoMapper.makeResponse(CategoryResponseDto.class, categories);
    }

    @Override
    public PaginatedDataResponse getCategoryById(Long id) {
        if (id == null)
            throw new NotFoundException("Not found Category");

        Category category = categoryRepository
                .findOne(categorySpec.getCategoryById(id))
                .orElseThrow(() -> new NotFoundException("Not found Category"));

        return dtoMapper.makeResponse(CategoryResponseDto.class, category);
    }

    @Override
    public PaginatedDataResponse createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findByName(categoryRequestDto.getName());
        if (category!= null) {
            category.updateRecordStatus(RECORD_STATUS.ACTIVE);
        } else {
            category = new Category(categoryRequestDto);
        }

        category = categoryRepository.save(category);
        return dtoMapper.makeResponse(CategoryResponseDto.class, category);
    }

    @Override
    public PaginatedDataResponse updateCategory(CategoryRequestDto categoryRequestDto) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryRequestDto.getId());
        Category categoryExist = categoryOpt
                .orElseThrow(() -> new BadRequestException("Not found Category"));

        categoryExist.update(categoryRequestDto);
        categoryExist = categoryRepository.save(categoryExist);

        return dtoMapper.makeResponse(CategoryResponseDto.class, categoryExist);
    }

    @Override
    @Transactional
    public void deleteCategory(List<Long> categoryIds) {
        List<Category> categoryExists = categoryRepository.findAll(categorySpec.getCategoryByIds(categoryIds));
        categoryExists.forEach(categoryExist -> categoryExist.updateRecordStatus(RECORD_STATUS.DELETED));

        categoryRepository.saveAll(categoryExists);
    }
}
