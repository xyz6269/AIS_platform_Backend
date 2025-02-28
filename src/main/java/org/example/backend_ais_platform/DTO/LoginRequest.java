package org.example.backend_ais_platform.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password
) {}
