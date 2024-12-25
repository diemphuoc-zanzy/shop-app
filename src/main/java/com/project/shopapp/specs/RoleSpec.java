package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.request.RoleRequestDto;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.Role_;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleSpec {

    public Specification<Role> getCategories(RoleRequestDto categoryRequestDto) {
        return (root, query, builder) -> {
            BasePredicate<Role> predicate = new BasePredicate<>();
            if (StringUtils.isNotEmpty(categoryRequestDto.getName())) {
                predicate.addLikeCondition(builder, root, Role_.NAME, categoryRequestDto.getName());
            }
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Role> getRoleById(Long id) {
        return (root, query, builder) -> {
            BasePredicate<Role> predicate = new BasePredicate<>();
            predicate.addEqualCondition(builder, root, Role_.ID, id);
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Role> getRoleByIds(List<Long> categoryIds) {
        return ((root, query, builder) -> {
            BasePredicate<Role> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, Role_.ID, categoryIds);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            return predicate.toPredicate(builder);
        });
    }
}
