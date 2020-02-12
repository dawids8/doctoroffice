package com.doctorsoffice.doctor;

import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(doctor.getId(), doctor.getFirstname(), doctor.getLastname(), doctor.getMedicalSpecialization());
    }

    public Doctor fromDto(DoctorDto doctorDto) {
        return new Doctor(doctorDto.getId(), doctorDto.getFirstname(), doctorDto.getLastname(),
                doctorDto.getMedicalSpecialization());
    }
}
