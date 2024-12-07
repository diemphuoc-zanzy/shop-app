package com.project.shopapp.common.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Setter
@Getter
public class PaginationDetails {
    private int pageSize;
    private int pageNumber;
    private long totalElements;
    private int totalPages;

    public PaginationDetails(Page<?> page) {
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber() + 1;
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
