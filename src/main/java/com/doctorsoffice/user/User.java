package com.doctorsoffice.user;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME", unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstname;

    @Column(name = "LAST_NAME")
    @NotNull
    private String lastname;

    @Column(name = "DATE_OF_BIRTH")
    @NotNull
    private LocalDate dateOfBirth;

    @Column(name = "PESEL", unique = true)
    private String pesel;

    @Column(name = "EMAIL", unique = true)
    @NotNull
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true)
    @NotNull
    private String phoneNumber;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POST_CODE")
    private String postCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    @NotNull
    private UserRole userRole;

    public User() {
    }

    public User(Long id, String username, String password, String firstname, String lastname, LocalDate dateOfBirth,
                String pesel, String email, String phoneNumber, String street, String city, String postCode, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.userRole = userRole;
    }
}
