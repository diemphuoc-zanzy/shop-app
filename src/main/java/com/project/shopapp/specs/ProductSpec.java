package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import com.project.shopapp.dtos.request.ProductRequestDto;
import com.project.shopapp.models.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpec {

    public Specification<Product> getProducts(ProductRequestDto productRequestDto) {
        return (root, query, builder) -> {
            BasePredicate<Product> predicate = new BasePredicate<>();

            Join<Category, Product> join = root.join(Product_.CATEGORY, JoinType.LEFT);

            predicate.addEqualCondition(builder, join, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Kiểm tra nếu tên không rỗng, áp dụng điều kiện LIKE
            if (StringUtils.isNotEmpty(productRequestDto.getName())) {
                predicate.addLikeCondition(builder, root, Product_.NAME, productRequestDto.getName());
            }

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Product> getProductById(Long productId) {
        return (root, query, builder) -> {
            BasePredicate<Product> predicate = new BasePredicate<>();

            Join<Category, Product> join = root.join(Product_.CATEGORY, JoinType.LEFT);

            predicate.addEqualCondition(builder, root, Product_.ID, productId);

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, join, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Product> getProductByIds(List<Long> productIds) {
        return (root, query, builder) -> {
            BasePredicate<Product> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, Product_.ID, productIds);

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }
}
