package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.patient.Patient;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@NamedEntityGraph(name = "Appointment.detail", attributeNodes = {@NamedAttributeNode("patient"),
                                                                    @NamedAttributeNode("doctor")})
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
    private String diagnosis;

    @Column(name = "PRESCRIPTION")
    private String prescription;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_FK")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "PATIENT_FK")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppointmentStatus appointmentStatus;

    public Appointment() {
    }

    public Appointment(Long id, LocalDateTime date, String diagnosis, String prescription, Doctor doctor, Patient patient,
                       AppointmentStatus appointmentStatus) {
        this.id = id;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentStatus = appointmentStatus;
    }
}
