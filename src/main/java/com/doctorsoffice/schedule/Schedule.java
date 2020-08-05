package com.doctorsoffice.schedule;

import com.doctorsoffice.doctor.Doctor;
import lombok.Data;

import javax.persistence.*;
import javax.print.Doc;
import java.time.LocalTime;

@Entity(name = "SCHEDULE")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private Long id;

    @Column(name = "START")
    private LocalTime start;

    @Column(name = "FINISH")
    private LocalTime finish;

    @Column(name = "APPOINTMENT_DURATION")
    private Long appointmentDuration;

    @Column(name = "INTERVAL_MIN")
    private Long intervalMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "WEEK_DAY")
    private WeekDay weekDay;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_FK")
    private Doctor doctor;

    public Schedule() {
    }

    public Schedule(LocalTime start, LocalTime finish, Long appointmentDuration, Long intervalMinutes, WeekDay weekDay, Doctor doctor) {
        this.start = start;
        this.finish = finish;
        this.appointmentDuration = appointmentDuration;
        this.intervalMinutes = intervalMinutes;
        this.weekDay = weekDay;
        this.doctor = doctor;
    }
}
