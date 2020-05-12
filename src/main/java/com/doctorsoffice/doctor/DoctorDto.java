package com.doctorsoffice.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DoctorDto {

    private Long id;
    private MedicalSpecialization medicalSpecialization;

}
