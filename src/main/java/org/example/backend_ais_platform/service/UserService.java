package org.example.backend_ais_platform.service;

import lombok.RequiredArgsConstructor;
import org.example.backend_ais_platform.DTO.LoginRequest;
import org.example.backend_ais_platform.DTO.SignupRequest;
import org.example.backend_ais_platform.DTO.VerificationDTO;
import org.example.backend_ais_platform.email.MailService;
import org.example.backend_ais_platform.entity.User;
import org.example.backend_ais_platform.enums.Cellule;
import org.example.backend_ais_platform.enums.Gender;
import org.example.backend_ais_platform.enums.Role;
import org.example.backend_ais_platform.exceptions.UserNotFoundException;
import org.example.backend_ais_platform.redis.VerificationCodeCache;
import org.example.backend_ais_platform.repository.UserRepository;
import org.example.backend_ais_platform.security.SecurityConfig;
import org.example.backend_ais_platform.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig config;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private final VerificationCodeCache verificationCodeCache;


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
        newUser.setCycle(request.cycle());
        newUser.setMajor(request.major());
        newUser.setGender(Gender.valueOf(request.gender()));
        newUser.setDateOfBirth(request.dateOfBirth().toInstant());
        newUser.setPassword(config.passwordEncoder().encode(request.password()));
        newUser.setRole(Role.USER);

        userRepository.save(newUser);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () ->
                                new UserNotFoundException("no user available accounts with this id"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () ->
                                new UserNotFoundException("no user available accounts with this email: "+ email));
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(
                        () ->
                                new UserNotFoundException("no user available accounts with this phone number: "+ phoneNumber));
    }

    public void recoverUserAccount(String email) {
        var userAccountToRecover = findUserByEmail(email);
        if (verificationCodeCache.getVerificationCode(email) != null) {
            throw new RuntimeException("this user already has an active verification code");
        }

        String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        mailService.sendEmail(
                email,
                "account verification via email",
                "in order to reset your account password please type code below :" + code
        );
        verificationCodeCache.saveVerificationCode(email, code);
    }

    public void inputVerificationCode(VerificationDTO verification) {
        String verificationCode = verificationCodeCache.getVerificationCode(verification.email());
        if (!verificationCode.equals(verification.code())) throw new RuntimeException("incorrect code");
    }

    public void changePassword(LoginRequest request) {
        User user = findUserByEmail(request.email());
        user.setPassword(config.passwordEncoder().encode(request.password()));
        userRepository.save(user);
        verificationCodeCache.deleteVerificationCode(verification.email());
    }


//    public void makeAdmin() {
//        User admin = new User();
//        admin.setFirstName("admin");
//        admin.setLastName("admin");
//        admin.setEmail("admin@email.com");
//        admin.setPhoneNumber("+1-234-567-8901");
//        admin.setCycle("whatever");
//        admin.setMajor("whatever");
//        admin.setDateOfBirth(Instant.ofEpochSecond(2002-01-23));
//        admin.setPassword(config.passwordEncoder().encode("admin"));
//        admin.setRole(Role.ADMIN);
//        admin.setGender(Gender.MALE);
//        admin.setCellule(Cellule.PROJECT);
//
//        userRepository.save(admin);
//    }
}
