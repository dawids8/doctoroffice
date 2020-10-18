package com.doctorsoffice.appointment;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GenerateAppointmentsRequest {

    private String doctorUsername;
    private LocalDate startDate;
    private LocalDate endDate;
}
