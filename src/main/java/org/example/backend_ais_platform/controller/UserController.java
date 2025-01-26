package org.example.backend_ais_platform.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend_ais_platform.DTO.LoginRequest;
import org.example.backend_ais_platform.DTO.SignupRequest;
import org.example.backend_ais_platform.DTO.VerificationDTO;
import org.example.backend_ais_platform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/recover-user-account/{email}")
    public ResponseEntity<String> recoverUserAccount(@PathVariable("email") String email) {
        userService.recoverUserAccount(email);
        return ResponseEntity.ok("user account found");
    }

    @PostMapping("/submit-code")
    public ResponseEntity<String> inputVerificationCode(@Validated @RequestBody VerificationDTO verification) {
        userService.inputVerificationCode(verification);
        return ResponseEntity.ok("verification code is correct");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Validated @RequestBody LoginRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok("password changed successfully");
    }
}
