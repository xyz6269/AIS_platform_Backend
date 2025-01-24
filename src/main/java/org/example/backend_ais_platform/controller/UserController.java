package org.example.backend_ais_platform.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend_ais_platform.DTO.LoginRequest;
import org.example.backend_ais_platform.DTO.SignupRequest;
import org.example.backend_ais_platform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("/api/v1/ais/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.logIn(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok("user created successfully");
    }
}
