package com.project.shopapp.controller;

import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.dtos.request.UserRequestDto;
import com.project.shopapp.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<PaginatedDataResponse> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
            PaginatedDataResponse response = userService.register(userRequestDto);
            return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<PaginatedDataResponse> loginUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        PaginatedDataResponse response = userService.login(userRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<PaginatedDataResponse> refreshToken() {
        PaginatedDataResponse response = userService.refresh();
        return ResponseEntity.ok().body(response);
    }
}
