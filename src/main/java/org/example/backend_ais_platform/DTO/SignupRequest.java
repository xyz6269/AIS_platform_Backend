package org.example.backend_ais_platform.DTO;


import jakarta.validation.constraints.*;

import java.time.ZonedDateTime;


public record SignupRequest(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @Pattern(regexp = "(\\+\\d{0,2})?((-|\\w|\\s)?\\d{3,4}){3,4}") // Basic International Phone Regex
        // I'd be a bit more strict to ensure a more normalized database
        String phoneNumber,

        @Past
        ZonedDateTime dateOfBirth,

        String cycle,

        String major,

        @NotBlank
        String gender,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 5, max = 25)
        String password
) {
    @AssertTrue(message = "{password.includes.name}")
    // The above is a MessageSource Integration, see
    // https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-messagesource
    public boolean passwordDoesNotIncludeName() {
        return password.contains(firstName) || password.contains(lastName);
    }}
