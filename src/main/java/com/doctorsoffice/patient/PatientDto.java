package com.doctorsoffice.patient;

import lombok.Data;

@Data
public class PatientDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private String street;
    private String city;
    private String postCode;
    private String phoneNumber;
    private String email;

    public PatientDto(Long id, String firstname, String lastname, String pesel, String street, String city, String postCode,
                      String phoneNumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
