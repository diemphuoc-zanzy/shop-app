package com.project.shopapp.dtos.request.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private int pageSize = 10;

    private int pageNumber = 0;

    private String sortBy;

    private String direction = "asc";

    // Phương thức chuyển đổi sang Pageable
    public Pageable toPageable() {
        if (StringUtils.isEmpty(sortBy)) {
            return PageRequest.of(getPageNumber(), getPageSize());
        }

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return PageRequest.of(getPageNumber(), getPageSize(), sort);

    }
}
