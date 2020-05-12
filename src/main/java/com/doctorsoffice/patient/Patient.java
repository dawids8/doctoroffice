package com.doctorsoffice.patient;

import com.doctorsoffice.appointment.Appointment;
import com.doctorsoffice.user.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private Long id;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "USER_FK")
    private User user;

    public Patient() {
    }

    public Patient(Long id) {
        this.id = id;
    }
}
