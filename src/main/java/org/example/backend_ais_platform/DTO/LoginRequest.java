package org.example.backend_ais_platform.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String email;
    private String password;
}
