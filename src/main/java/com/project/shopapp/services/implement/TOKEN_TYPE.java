package com.project.shopapp.services.implement;

import lombok.Getter;

@Getter
public enum TOKEN_TYPE {
    ACCESS_TOKEN("access_token"),
    REFRESH_TOKEN("refresh_token");

    private final String tokenType;

    TOKEN_TYPE(String tokenType) {
        this.tokenType = tokenType;
    }


}
