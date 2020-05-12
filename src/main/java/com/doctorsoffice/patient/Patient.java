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

    @Column(name = "FIRST_NAME")
    private String firstname;

    @Column(name = "LAST_NAME")
    private String lastname;

    @Column(name = "PESEL", unique = true)
    private String pesel;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "PHONE_NUMBER", unique = true)
    private String phoneNumber;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "USER_FK")
    private User user;

    public Patient() {
    }

    public Patient(Long id, String firstname, String lastname, String pesel, String street, String city, String postCode,
                   String phoneNumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.street = street;
        this.city = city;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
