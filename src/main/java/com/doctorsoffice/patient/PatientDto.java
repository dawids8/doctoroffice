package com.doctorsoffice.patient;

import lombok.Data;

@Data
public class PatientDto {

    private Long id;

    public PatientDto(Long id) {
        this.id = id;
    }
}
