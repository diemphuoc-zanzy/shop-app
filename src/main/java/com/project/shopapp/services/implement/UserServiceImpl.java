package com.project.shopapp.services.implement;

import com.project.shopapp.common.constant.Constant.CHARACTER;
import com.project.shopapp.common.constant.MessageKeys;
import com.project.shopapp.dtos.response.TokenResponseDto;
import com.project.shopapp.services.IRoleService;
import com.project.shopapp.services.ITokenService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.utils.CookieUtil;
import com.project.shopapp.dtos.response.UserResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import com.project.shopapp.dtos.request.UserRequestDto;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.IUserService;
import com.project.shopapp.utils.DtoMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final IRoleService roleService;
    private final ITokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final DtoMapperUtils mapper;
    private final CookieUtil cookieUtil;

    @Value("${jwt.refreshToken}")
    private int refreshTokenLive;

    @Override
    public PaginatedDataResponse register(UserRequestDto userRequestDto) {
        if (!userRequestDto.isMatchWithRetypePassword())
            throw new BadRequestException(this.message(MessageKeys.USER.REGISTER_RETYPE));

        User newUser = userRepository
                .findByPhoneNumber(userRequestDto.getPhoneNumber())
                .orElse(null);

        if (newUser != null) {
            if (newUser.isActive()) {
                throw new UnauthorizedAccessException(this.message(MessageKeys.USER.REGISTER_UN_ACTIVE));
            }
            throw new BadRequestException(this.message(MessageKeys.USER.REGISTER_EXIST));
        }

        newUser = new User(userRequestDto);

        Role role = roleService.getRole(userRequestDto.getRoleId());

        newUser.setRole(role);
        if (userRequestDto.getFacebookAccountId() == 0
                && userRequestDto.getGoogleAccountId() == 0) {
            String password = userRequestDto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }

        newUser = userRepository.save(newUser);
        tokenService.iCreateRefreshToken(newUser);
        return mapper.makeResponse(UserResponseDto.class, newUser);
    }

    @Override
    public PaginatedDataResponse login(UserRequestDto userRequestDto) {
        User existUser = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber())
                .orElseThrow(() -> new BadRequestException(this.message(MessageKeys.USER.LOGIN_INVALID)));

        if (userRequestDto.getFacebookAccountId() == 0
                && userRequestDto.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(userRequestDto.getPassword(), existUser.getPassword())) {
                throw new BadRequestException(this.message(MessageKeys.USER.LOGIN_INVALID));
            }
        }

        // Creating authentication token
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userRequestDto.getPhoneNumber(),
                        userRequestDto.getPassword(),
                        existUser.getAuthorities()
                );

        // Authenticate with Spring Security
        authenticationManager.authenticate(authenticationToken);

        // Generate tokens
        String newAccessToken = tokenService.iCreateAccessToken(existUser);
        makeCookieRefreshTokenByUser(existUser);

        // Return Access Token and response
        return new PaginatedDataResponse(new TokenResponseDto(newAccessToken)); // Only return accessToken
    }


    @Override
    public PaginatedDataResponse refresh() {
        String refreshToken = cookieUtil.getCookieValue(TOKEN_TYPE.REFRESH_TOKEN.getTokenType())
                .orElseThrow(() -> new UnauthorizedAccessException("Refresh token is missing"));

        User existUser = tokenService.iGetUserByToken(refreshToken);

        String newAccessToken = tokenService.iCreateAccessToken(existUser);
        makeCookieRefreshTokenByUser(existUser);

        // Trả về Access Token mới
        return new PaginatedDataResponse(new TokenResponseDto(newAccessToken));
    }

    private void makeCookieRefreshTokenByUser(User user) {
        String refreshToken = tokenService.iCreateRefreshToken(user);
        String tokenType = TOKEN_TYPE.REFRESH_TOKEN.getTokenType();
        // Set Refresh Token in a cookie
        cookieUtil.createCookie(
                tokenType, refreshToken, refreshTokenLive, true, true, CHARACTER.SLASH
        );
    }

}
