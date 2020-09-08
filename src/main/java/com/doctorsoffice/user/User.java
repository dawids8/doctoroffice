package com.doctorsoffice.user;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
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

    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @OneToOne(mappedBy = "user")
    private Patient patient;

    public User() {
    }
}
