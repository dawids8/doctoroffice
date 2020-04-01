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

    @OneToOne
    @JoinColumn(name = "DOCTOR_FK")
    private Doctor doctor;

    public Schedule() {
    }

    public Schedule(Long id, LocalTime start, LocalTime finish, Long intervalMinutes) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.intervalMinutes = intervalMinutes;
    }
}
