package com.doctorsoffice.appointment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentRequest {

    private Long doctorId;
    private LocalDateTime appointmentDate;

}
