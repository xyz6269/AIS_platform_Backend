package org.example.backend_ais_platform.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String cycle;

    private String major;

    private String email;

    private String password;

    private String cellule;

    private Integer nbAbs;

    private Integer rank;

}
