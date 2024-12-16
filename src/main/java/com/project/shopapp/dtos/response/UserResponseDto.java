package com.project.shopapp.dtos.response;

import com.project.shopapp.models.User;
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

    private Instant dateOfBirth;

    private int facebookAccountId;

    private int googleAccountId;

    private Long roleId;


    public UserResponseDto(User user) {
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.dateOfBirth = user.getDateOfBirth();
    }
}
