package com.doctorsoffice.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String pesel;
    private String email;
    private String phoneNumber;
    private String street;
    private String city;
    private String postCode;
    private String userRole;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String firstname, String lastname, LocalDate dateOfBirth,
                   String pesel, String email, String phoneNumber, String street, String city, String postCode, String userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.userRole = userRole;
    }
}
