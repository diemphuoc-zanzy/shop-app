package com.project.shopapp.services;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.UserRequestDto;

public interface IUserService {
    PaginatedDataResponse createUser(UserRequestDto userRequestDto);
    PaginatedDataResponse login(UserRequestDto userRequestDto);

}
