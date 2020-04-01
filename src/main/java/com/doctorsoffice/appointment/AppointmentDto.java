package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.DoctorDto;
import com.doctorsoffice.patient.PatientDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long id;
    private LocalDateTime date;
    private String diagnosis;
    private String prescription;
    private DoctorDto doctorDto;
    private PatientDto patientDto;
    private Status status;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, LocalDateTime date, String diagnosis, String prescription, DoctorDto doctorDto,
                          PatientDto patientDto, Status status) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.doctorDto = doctorDto;
        this.patientDto = patientDto;
        this.status = status;
    }
}
