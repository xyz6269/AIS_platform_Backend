package org.example.backend_ais_platform.DTO;

import jakarta.validation.constraints.*;

import java.time.ZonedDateTime;


public record UserDTO(

        String firstName,

        String lastName,

        String phoneNumber,

        @Past
        ZonedDateTime dateOfBirth,

        String cycle,

        String major,

        @Email
        String email,

        String password,

        String gender,

        String cellule,

        Integer nbAbs,

        Integer rank
) {}
