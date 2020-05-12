package com.doctorsoffice.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String userRole;

    public UserDto() {
    }

    public UserDto(Long id, String username, String firstname, String lastname, LocalDate dateOfBirth, String email,
                   String phoneNumber, String userRole) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
    }
}
