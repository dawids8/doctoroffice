package com.doctorsoffice.schedule;

import com.doctorsoffice.doctor.Doctor;
import lombok.Data;

import javax.persistence.*;
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

    @Column(name = "INTERVAL_MIN")
    private Long intervalMinutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "WEEK_DAY")
    private WeekDay weekDay;

    @ManyToOne
    @JoinColumn(name = "DOCTOR_FK")
    private Doctor doctor;


    public Schedule(LocalTime start, LocalTime finish, Long intervalMinutes, WeekDay weekDay) {
        this.start = start;
        this.finish = finish;
        this.intervalMinutes = intervalMinutes;
        this.weekDay = weekDay;
    }
}
