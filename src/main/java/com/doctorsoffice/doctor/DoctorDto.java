package com.doctorsoffice.doctor;

import lombok.Data;

@Data
public class DoctorDto {

    private Long id;
    private String firstname;
    private String lastname;
    private MedicalSpecialization medicalSpecialization;

    public DoctorDto(Long id, String firstname, String lastname, MedicalSpecialization medicalSpecialization) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.medicalSpecialization = medicalSpecialization;
    }

}
