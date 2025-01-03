package com.project.shopapp.services;

import com.project.shopapp.models.User;

public interface ITokenService {
    String iCreateAccessToken(User user);
    String iCreateRefreshToken(User user);
    User iGetUserByToken(String token);
}
