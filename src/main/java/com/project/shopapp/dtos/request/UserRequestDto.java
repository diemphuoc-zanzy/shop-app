package com.project.shopapp.dtos.request;

import com.project.shopapp.dtos.request.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto extends BaseDto {

    private String fullName;

    private String phoneNumber;

    private String address;

    private String password;

    private String retypePassword;

    private Instant dateOfBirth;

    private int facebookAccountId;

    private int googleAccountId;

    private Long roleId;

    public boolean isMatchWithRetypePassword() {
        return Objects.equals(password, retypePassword);
    }
}
