package com.doctorsoffice.user;

import com.doctorsoffice.doctor.MedicalSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
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
    private MedicalSpecialization medicalSpecialization;

    public UserDto() {
    }
}
