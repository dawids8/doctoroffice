package com.doctorsoffice.patient;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        if(patient == null) {
            return null;
        }

        return new PatientDto(patient.getId());
    }

    public List<PatientDto> toDto(List<Patient> patientsList) {
        return patientsList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Patient fromDto(PatientDto patientDto) {
        return new Patient(patientDto.getId());
    }

}
