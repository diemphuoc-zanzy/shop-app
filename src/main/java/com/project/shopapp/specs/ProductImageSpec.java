package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.models.ProductImage_;
import com.project.shopapp.models.Product_;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductImageSpec {

    public Specification<ProductImage> countProductImageByProductId(Long productId) {
        return (root, query, builder) -> {
            BasePredicate<ProductImage> predicate = new BasePredicate<>();

            Join<Product, ProductImage> join = root.join(ProductImage_.PRODUCT, JoinType.LEFT);

            predicate.addEqualCondition(builder, join, Product_.ID, productId);

            predicate.addEqualCondition(builder, join, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }

    public Specification<ProductImage> getProductImageByIds(List<Long> productImageIds) {
        return ((root, query, builder) -> {
            BasePredicate<ProductImage> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, ProductImage_.ID, productImageIds);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        });
    }
}
