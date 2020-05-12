package com.doctorsoffice.doctor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public DoctorDto toDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .medicalSpecialization(doctor.getMedicalSpecialization())
                .build();
    }

    public List<DoctorDto> toDto(List<Doctor> doctorsList) {
        return doctorsList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Doctor fromDto(DoctorDto doctorDto) {
        return Doctor.builder()
                .id(doctorDto.getId())
                .medicalSpecialization(doctorDto.getMedicalSpecialization())
                .build();
    }
}
