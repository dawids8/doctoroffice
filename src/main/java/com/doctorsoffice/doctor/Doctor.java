package com.doctorsoffice.doctor;

import com.doctorsoffice.appointment.Appointment;
import com.doctorsoffice.schedule.Schedule;
import com.doctorsoffice.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCTOR_ID")
    private Long id;

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

}
