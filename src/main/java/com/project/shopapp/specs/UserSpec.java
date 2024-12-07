package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import com.project.shopapp.models.User;
import com.project.shopapp.models.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpec {

    public Specification<User> getUserByPhoneNumber(String phoneNumber) {
        return (root, query, builder) -> {
            BasePredicate<User> predicate = new BasePredicate<>();
            predicate.addEqualCondition(builder, root, User_.PHONE_NUMBER, phoneNumber);
            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }
}
