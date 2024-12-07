package com.project.shopapp.dtos.response;

import com.project.shopapp.dtos.request.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String fullName;

    private String phoneNumber;

    private String address;

    private String password;

    private String retypePassword;

    private Instant dateOfBirth;

    private int facebookAccountId;

    private int googleAccountId;

    private Long roleId;
}
