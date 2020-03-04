package com.doctorsoffice.patient;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient) {
        return new PatientDto(patient.getId(), patient.getFirstname(), patient.getLastname(), patient.getPesel(),
                patient.getStreet(), patient.getCity(), patient.getPostCode(), patient.getPhoneNumber(), patient.getEmail());
    }

    public List<PatientDto> toDto(List<Patient> patientsList) {
        return patientsList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Patient fromDto(PatientDto patientDto) {
        return new Patient(patientDto.getId(), patientDto.getFirstname(), patientDto.getLastname(), patientDto.getPesel(),
                patientDto.getStreet(), patientDto.getCity(), patientDto.getPostCode(), patientDto.getPhoneNumber(),
                patientDto.getEmail());
    }

}
