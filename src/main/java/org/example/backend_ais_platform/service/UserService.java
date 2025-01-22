package org.example.backend_ais_platform.service;

import lombok.RequiredArgsConstructor;
import org.example.backend_ais_platform.DTO.LoginRequest;
import org.example.backend_ais_platform.DTO.SignupRequest;
import org.example.backend_ais_platform.entity.User;
import org.example.backend_ais_platform.exceptions.RoleNotFoundException;
import org.example.backend_ais_platform.exceptions.UserNotFoundException;
import org.example.backend_ais_platform.repository.RoleRepository;
import org.example.backend_ais_platform.repository.UserRepository;
import org.example.backend_ais_platform.security.SecurityConfig;
import org.example.backend_ais_platform.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
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
    private final RoleRepository roleRepository;

    public ResponseEntity<String> logIn(LoginRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        return ResponseEntity.ok(jwt);
    }

    public ResponseEntity<String> signUp(SignupRequest request) {
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setCellule(request.getCellule());
        newUser.setCycle(request.getCycle());
        newUser.setMajor(request.getMajor());
        newUser.setDateOfBirth(request.getDateOfBirth());
        newUser.setPassword(config.passwordEncoder().encode(request.getPassword()));
        newUser.setRole(roleRepository.findRoleByRoleName("role_user").orElseThrow(() -> new RoleNotFoundException("no such roles")));
        userRepository.save(newUser);
        return ResponseEntity.ok("User created successfully");
    }
}
