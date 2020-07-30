package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.DoctorDto;
import com.doctorsoffice.patient.PatientDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDto {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String diagnosis;
    private String prescription;
    private DoctorDto doctorDto;
    private PatientDto patientDto;
    private AppointmentStatus appointmentStatus;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, LocalDateTime startDate , LocalDateTime endDate, String diagnosis, String prescription, DoctorDto doctorDto,
                          PatientDto patientDto, AppointmentStatus appointmentStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.doctorDto = doctorDto;
        this.patientDto = patientDto;
        this.appointmentStatus = appointmentStatus;
    }
}
