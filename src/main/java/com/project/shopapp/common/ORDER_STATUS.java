package com.project.shopapp.common;

import lombok.Getter;

@Getter
public enum ORDER_STATUS {
    PENDING("Pending"),
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String status;

    ORDER_STATUS(String status) {
        this.status = status;
    }

}
