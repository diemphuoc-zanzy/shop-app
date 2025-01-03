package com.project.shopapp.specs.base;

import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasePredicate<T> {

    protected final List<Predicate> predicates = new ArrayList<>();

    /**
     * Get an "equal" condition to the predicate.
     */
    private static <T> Predicate getEqual(CriteriaBuilder builder,
                                          Path<T> root,
                                          String field,
                                          Object value) {
        return builder.equal(root.get(field), value);
    }

    /**
     * Get a "like" condition to the predicate.
     */
    private static <T> Predicate getLike(CriteriaBuilder builder,
                                         Path<T> root,
                                         String field,
                                         String value) {
        return builder.like(root.get(field), "%" + value + "%");
    }

    /**
     * Get a "like" condition to the predicate.
     */
    private static <T> Predicate getIn(Path<T> root, String field, List<?> values) {
        return root.get(field).in(values);
    }

    /**
     * Adds a "non-equal" condition to the predicate list.
     */
    public void addNonEqualCondition(CriteriaBuilder builder, Path<T> root, String field, Object value) {
        if (value != null) {
            predicates.add(getEqual(builder, root, field, value).not());
        }
    }

    /**
     * Adds an "equal" condition to the predicate list.
     */
    public void addEqualCondition(CriteriaBuilder builder, Path<T> root, String field, Object value) {
        if (value != null) {
            predicates.add(getEqual(builder, root, field, value));
        }
    }

    /**
     * Adds a "non-like" condition to the predicate list.
     */
    public void addNonLikeCondition(CriteriaBuilder builder, Path<T> root, String field, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        predicates.add(getLike(builder, root, field, value).not());
    }

    /**
     * Adds a "like" condition to the predicate list.
     */
    public void addLikeCondition(CriteriaBuilder builder, Path<T> root, String field, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        predicates.add(getLike(builder, root, field, value));
    }

    /**
     * Adds a "non-in" condition to the predicate list.
     */
    public void addNonInCondition(CriteriaBuilder builder, Path<T> root, String field, List<?> values) {
        if (values == null || values.isEmpty()) {
            return;
        }
        predicates.add(getIn(root, field, values).not());
    }

    /**
     * Adds an "in" condition to the predicate list.
     */
    public void addInCondition(CriteriaBuilder builder, Path<T> root, String field, List<?> values) {
        if (values == null || values.isEmpty()) {
            return;
        }
        predicates.add(getIn(root, field, values));
    }


    /**
     * Adds a "greater than" condition to the predicate list.
     */
    public void addGreaterThanCondition(CriteriaBuilder builder, Path<T> root, String field, Long value) {
        if (value != null) {
            predicates.add(builder.greaterThan(root.get(field), value));
        }
    }

    /**
     * Adds a "less than" condition to the predicate list.
     */
    public void addLessThanCondition(CriteriaBuilder builder, Path<T> root, String field, Long value) {
        if (value != null) {
            predicates.add(builder.lessThan(root.get(field), value));
        }
    }

    /**
     * Adds a "between" condition to the predicate list.
     */
    public void addBetweenCondition(CriteriaBuilder builder, Path<T> root, String field, Long start, Long end) {
        if (start != null && end != null) {
            predicates.add(builder.between(root.get(field), start, end));
        }
    }

    /**
     * Combines all predicates into one.
     */
    public Predicate toPredicate(CriteriaBuilder builder) {
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
