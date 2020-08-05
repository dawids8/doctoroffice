package com.doctorsoffice.schedule;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class ScheduleMapper {

    public ScheduleDto toDto(Schedule schedule) {
        return new ScheduleDto(schedule.getId(), schedule.getStart(), schedule.getFinish(), schedule.getAppointmentDuration(),
                schedule.getIntervalMinutes(), schedule.getWeekDay());
    }

    public List<ScheduleDto> toDto(List<Schedule> scheduleList) {
        return scheduleList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /*public Schedule fromDto(ScheduleDto scheduleDto) {
        return new Schedule();
    }*/
}
