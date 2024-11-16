package com.example.demo.controller;

import com.example.demo.dto.user.UserCreateRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    @Operation(summary = "회원가입", description = "회원을 가입합니다.")
    public ResponseEntity<UserResponse> createRecipe(@RequestBody UserCreateRequest userCreateRequest) {
        log.info("회원가입 요청 : {}", userCreateRequest);
        UserResponse userResponse = userService.createUser(userCreateRequest);
        return ResponseEntity.ok(userResponse);
    }
}
