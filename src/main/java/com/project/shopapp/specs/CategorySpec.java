package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Category_;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import com.project.shopapp.dtos.request.CategoryRequestDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Category_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorySpec {

    public Specification<Category> getCategories(CategoryRequestDto categoryRequestDto) {
        return (root, query, builder) -> {
            BasePredicate<Category> predicate = new BasePredicate<>();
            if (StringUtils.isNotEmpty(categoryRequestDto.getName())) {
                predicate.addLikeCondition(builder, root, Category_.NAME, categoryRequestDto.getName());
            }
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Category> getCategoryById(Long id) {
        return (root, query, builder) -> {
            BasePredicate<Category> predicate = new BasePredicate<>();
            predicate.addEqualCondition(builder, root, Category_.ID, id);
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Category> getCategoryByIds(List<Long> categoryIds) {
        return ((root, query, builder) -> {
            BasePredicate<Category> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, Category_.ID, categoryIds);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            return predicate.toPredicate(builder);
        });
    }
}
