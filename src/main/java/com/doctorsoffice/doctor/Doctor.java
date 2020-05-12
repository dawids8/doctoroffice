package com.doctorsoffice.doctor;

import com.doctorsoffice.appointment.Appointment;
import com.doctorsoffice.schedule.Schedule;
import com.doctorsoffice.user.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCTOR_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "PESEL", unique = true)
    private String pesel;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEDICAL_SPECIALIZATION")
    private MedicalSpecialization medicalSpecialization;

    @ToString.Exclude
    @OneToMany(mappedBy = "doctor")
    private List<Schedule> schedules;

    @ToString.Exclude
    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "USER_FK")
    private User user;

    public Doctor() {
    }

    public Doctor(Long id, String firstname, String lastname, String pesel, MedicalSpecialization medicalSpecialization) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.medicalSpecialization = medicalSpecialization;
    }
}
