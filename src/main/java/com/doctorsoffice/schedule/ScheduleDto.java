package com.doctorsoffice.schedule;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDto {

    private Long id;
    private LocalTime start;
    private LocalTime finish;
    private Long appointmentDuration;
    private Long intervalMinutes;
    private WeekDay weekDay;

    public ScheduleDto(Long id, LocalTime start, LocalTime finish, Long appointmentDuration, Long intervalMinutes, WeekDay weekDay) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.appointmentDuration = appointmentDuration;
        this.intervalMinutes = intervalMinutes;
        this.weekDay = weekDay;
    }
}
