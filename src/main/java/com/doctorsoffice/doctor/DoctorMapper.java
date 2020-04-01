package com.doctorsoffice.doctor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    public DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(doctor.getId(), doctor.getFirstname(), doctor.getPesel(), doctor.getLastname(),
                doctor.getMedicalSpecialization());
    }

    public List<DoctorDto> toDto(List<Doctor> doctorsList) {
        return doctorsList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Doctor fromDto(DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getFirstname(), doctorDto.getLastname(), doctorDto.getPesel(),
                doctorDto.getMedicalSpecialization());
    }
}
