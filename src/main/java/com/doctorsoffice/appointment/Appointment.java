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

    @Column(name = "START_DATE")
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    @NotNull
    private LocalDateTime endDate;

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

    public Appointment(Long id, LocalDateTime startDate, LocalDateTime endDate, String diagnosis, String prescription, Doctor doctor, Patient patient,
                       AppointmentStatus appointmentStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentStatus = appointmentStatus;
    }

    public Appointment(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor, AppointmentStatus appointmentStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.doctor = doctor;
        this.appointmentStatus = appointmentStatus;
    }
}
