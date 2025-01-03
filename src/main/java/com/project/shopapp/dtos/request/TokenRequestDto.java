package com.project.shopapp.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {
    private String phoneNumber;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long roleId;

    public TokenRequestDto(String phoneNumber, String tokenType) {
        this.phoneNumber = phoneNumber;
        this.tokenType = tokenType;
    }
}
