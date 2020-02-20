package com.doctorsoffice.patient;

import lombok.Data;

@Data
public class PatientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String postCode;
    private String phoneNumber;
    private String email;


}
