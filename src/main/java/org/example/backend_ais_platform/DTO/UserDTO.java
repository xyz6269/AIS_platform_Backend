package org.example.backend_ais_platform.DTO;

import jakarta.validation.constraints.*;

import java.time.ZonedDateTime;


public record UserDTO(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @Pattern(regexp = "(\\+\\d{0,2})?((-|\\w)?\\d{3,4}){3,4}") // Basic International Phone Regex
        // I'd be a bit more strict to ensure a more normalized database
        String phoneNumber,

        @Past
        ZonedDateTime dateOfBirth,

        String cycle,

        String major,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 15, max = 25)
        String password,

        String cellule,

        Integer nbAbs,

        Integer rank
) {}
