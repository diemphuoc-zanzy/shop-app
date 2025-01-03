package com.project.shopapp.specs;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.request.TokenRequestDto;
import com.project.shopapp.models.Token;
import com.project.shopapp.models.Token_;
import com.project.shopapp.models.User;
import com.project.shopapp.models.User_;
import com.project.shopapp.models.base.BaseModel_;
import com.project.shopapp.specs.base.BasePredicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenSpec {

    public Specification<Token> getTokens(TokenRequestDto tokenRequest) {
        return (root, query, builder) -> {
            BasePredicate<Token> predicate = new BasePredicate<>();

            Join<User, Token> joinUser = root.join(Token_.USER, JoinType.INNER);

            if (Strings.isNotEmpty(tokenRequest.getTokenType())) {
                predicate.addEqualCondition(builder, root, Token_.TOKEN_TYPE, tokenRequest.getTokenType());
            }

            if (StringUtils.isNotEmpty(tokenRequest.getPhoneNumber())) {
                predicate.addEqualCondition(builder, joinUser, User_.PHONE_NUMBER, tokenRequest.getPhoneNumber());
            }

            predicate.addEqualCondition(builder, root, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            predicate.addGreaterThanCondition(builder, root, Token_.EXPIRATION_DATE, Instant.now().toEpochMilli());
            predicate.addEqualCondition(builder, joinUser, BaseModel_.RECORD_STATUS, RECORD_STATUS.ACTIVE);
            return predicate.toPredicate(builder);
        };
    }

}
