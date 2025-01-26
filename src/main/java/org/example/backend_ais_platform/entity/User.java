package org.example.backend_ais_platform.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.backend_ais_platform.enums.Cellule;
import org.example.backend_ais_platform.enums.Gender;
import org.example.backend_ais_platform.enums.Role;

import java.time.Instant;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @Column(name = "cycle", length = 50, nullable = false)
    private String cycle;

    @Column(name = "major", length = 50, nullable = false)
    private String major;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "gender", length = 50, nullable = false)
    private Gender gender;

    @Column(name = "cellule", length = 50)
    private Cellule cellule;

    @Column(name = "nb_abs")
    private Integer nbAbs;

    @Column(name = "rank")
    private Integer rank;

    @Enumerated(EnumType.STRING)
    private Role role;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cellule getCellule() {
        return cellule;
    }

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public Integer getNbAbs() {
        return nbAbs;
    }

    public void setNbAbs(Integer nbAbs) {
        this.nbAbs = nbAbs;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
