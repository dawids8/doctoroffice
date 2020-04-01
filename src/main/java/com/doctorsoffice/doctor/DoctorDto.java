package com.doctorsoffice.doctor;

import lombok.Data;

@Data
public class DoctorDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String pesel;
    private MedicalSpecialization medicalSpecialization;

    public DoctorDto(Long id, String firstname, String lastname, String pesel, MedicalSpecialization medicalSpecialization) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.medicalSpecialization = medicalSpecialization;
    }
}
