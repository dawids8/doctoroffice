package com.doctorsoffice.appointment;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long id;

    @Column(name = "DATE")
    @NotNull
    private LocalDateTime date;

    @Column(name = "DIAGNOSIS")
    @NotNull
    private String diagnosis;

    @Column(name = "PRESCRIPTION")
    @NotNull
    private String prescription;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime date, String diagnosis, String prescription) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

}
