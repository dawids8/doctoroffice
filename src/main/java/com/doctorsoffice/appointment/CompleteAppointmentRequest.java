package com.doctorsoffice.appointment;

import lombok.Data;

@Data
public class CompleteAppointmentRequest {

    private Long appointmentId;
    private String diagnosis;
    private String prescription;

}
