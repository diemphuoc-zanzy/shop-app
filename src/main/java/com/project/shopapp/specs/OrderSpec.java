package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.request.OrderRequestDto;
import com.project.shopapp.models.*;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderSpec {

    public Specification<Order> getOrders(OrderRequestDto orderRequestDto) {
        return (root, query, builder) -> {
            BasePredicate<Order> predicate = new BasePredicate<>();

            predicate.addEqualCondition(builder, root, Order_.USER_ID, orderRequestDto.getUserId());

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Order> getOrderById(Long id) {
        return (root, query, builder) -> {
            BasePredicate<Order> predicate = new BasePredicate<>();

            predicate.addEqualCondition(builder, root, Order_.ID, id);

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }

    public Specification<Order> getOrderByIds(List<Long> orderIds) {
        return (root, query, builder) -> {
            BasePredicate<Order> predicate = new BasePredicate<>();

            predicate.addInCondition(builder, root, Order_.ID, orderIds);

            // Thêm điều kiện join với Category và "RECORD_STATUS = ACTIVE"
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);

            // Trả về predicate để xây dựng query
            return predicate.toPredicate(builder);
        };
    }
}
