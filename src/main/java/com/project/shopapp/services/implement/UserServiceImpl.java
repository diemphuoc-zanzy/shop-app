package com.project.shopapp.services.implement;

import com.project.shopapp.component.JwtToken;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.UnauthorizedAccessException;
import com.project.shopapp.dtos.request.UserRequestDto;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.RoleRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtToken jwtToken;
    private final AuthenticationManager authenticationManager;

    @Override
    public PaginatedDataResponse createUser(UserRequestDto userRequestDto) {
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

        Role role = roleRepository
                .findById(userRequestDto.getRoleId())
                .orElseThrow(() -> new UnauthorizedAccessException("Role Unauthorized Access"));

        newUser.setRole(role);
        if (userRequestDto.getFacebookAccountId() == 0
                && userRequestDto.getGoogleAccountId() == 0) {
            String password = userRequestDto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }

        newUser = userRepository.save(newUser);
        return new PaginatedDataResponse(newUser);
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
                userRequestDto.getPassword()
        );

        //authentication with Java Spring Security
        authenticationManager.authenticate(authenticationToken);
        String token = jwtToken.generateToken(existUser);
        return new PaginatedDataResponse(token);
    }
}
