package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.request.OrderDetailRequestDto;
import com.project.shopapp.models.*;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailSpec {

    public Specification<OrderDetail> getOrderDetails(OrderDetailRequestDto orderDto) {
        return (root, query, builder) -> {
            BasePredicate<OrderDetail> predicate = new BasePredicate<>();

            Join<Order, OrderDetail> joinOrder = root.join(OrderDetail_.ORDER, JoinType.LEFT);
            Join<Product, OrderDetail> joinProduct = root.join(OrderDetail_.PRODUCT, JoinType.LEFT);

            predicate.addEqualCondition(builder, joinOrder, Order_.ID, orderDto.getOrderId());
            predicate.addEqualCondition(builder, joinProduct, Product_.ID, orderDto.getProductId());

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            predicate.addEqualCondition(builder, joinOrder, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            predicate.addEqualCondition(builder, joinProduct, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            return predicate.toPredicate(builder);
        };
    }

    public Specification<OrderDetail> getOrderDetailById(Long orderId) {
        return (root, query, builder) -> {
            BasePredicate<OrderDetail> predicate = new BasePredicate<>();

            predicate.addEqualCondition(builder, root, OrderDetail_.ID, orderId);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            return predicate.toPredicate(builder);
        };
    }

    public Specification<OrderDetail> getOrderDetailByIds(List<Long> orderIds) {
        return (root, query, builder) -> {
            BasePredicate<OrderDetail> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, OrderDetail_.ID, orderIds);

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            return predicate.toPredicate(builder);
        };
    }
}
