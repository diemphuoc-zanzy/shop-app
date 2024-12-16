package com.project.shopapp.services.implement;

import com.project.shopapp.services.IRoleService;
import com.project.shopapp.utils.JwtTokenUtil;
import com.project.shopapp.dtos.response.UserResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import com.project.shopapp.dtos.request.UserRequestDto;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.IUserService;
import com.project.shopapp.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final DtoMapper mapper;

    @Override
    public PaginatedDataResponse register(UserRequestDto userRequestDto) {
        if (!userRequestDto.isMatchWithRetypePassword())
            throw new BadRequestException("Retype password is not match");

        User newUser = userRepository
                .findByPhoneNumber(userRequestDto.getPhoneNumber())
                .orElse(null);

        if (newUser != null) {
            if (newUser.isActive()) {
                throw new UnauthorizedAccessException("User Unauthorized Access");
            }
            throw new BadRequestException("User Exists");
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
        return mapper.makeResponse(UserResponseDto.class, newUser);
    }

    @Override
    public PaginatedDataResponse login(UserRequestDto userRequestDto) {
        User existUser = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber())
                .orElseThrow(() -> new BadRequestException("Invalid Phone Number/ Password"));

        if (userRequestDto.getFacebookAccountId() == 0
                && userRequestDto.getGoogleAccountId() == 0) {
            if (!passwordEncoder.matches(userRequestDto.getPassword(), existUser.getPassword())) {
                throw new BadRequestException("Invalid Phone Number/ Password");
            }
        }

        //creating authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userRequestDto.getPhoneNumber(),
                userRequestDto.getPassword(),
                existUser.getAuthorities()
        );

        //authentication with Java Spring Security
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtil.generateToken(existUser);
        return new PaginatedDataResponse(token);
    }
}
