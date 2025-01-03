package com.project.shopapp.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String accessToken;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String refreshToken;

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
