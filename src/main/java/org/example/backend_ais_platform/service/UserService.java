package org.example.backend_ais_platform.service;

import lombok.RequiredArgsConstructor;
import org.example.backend_ais_platform.DTO.LoginRequest;
import org.example.backend_ais_platform.DTO.SignupRequest;
import org.example.backend_ais_platform.entity.User;
import org.example.backend_ais_platform.enums.Role;
import org.example.backend_ais_platform.repository.UserRepository;
import org.example.backend_ais_platform.security.SecurityConfig;
import org.example.backend_ais_platform.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig config;
    private final AuthenticationManager authenticationManager;


    public String logIn(LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User u) {
            return JwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        }
        else {
            throw new RuntimeException("incorrect credentials");
        }
    }

    public void signUp(SignupRequest request) {

        User newUser = new User();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPhoneNumber(request.phoneNumber());
        newUser.setCellule(request.cellule());
        newUser.setCycle(request.cycle());
        newUser.setMajor(request.major());
        newUser.setDateOfBirth(request.dateOfBirth().toInstant());
        newUser.setPassword(config.passwordEncoder().encode(request.password()));
        newUser.setRole(Role.USER);

        userRepository.save(newUser);
    }

}
