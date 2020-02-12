package com.doctorsoffice.doctor;

import com.doctorsoffice.appointment.Appointment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Doctor {

    @Column(name = "DOCTOR_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "MEDICAL_SPECIALIZATION")
    private MedicalSpecialization medicalSpecialization;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointmentList;

    public Doctor() {
    }

    public Doctor(Long id, String firstname, String lastname, MedicalSpecialization medicalSpecialization) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.medicalSpecialization = medicalSpecialization;
    }
}
