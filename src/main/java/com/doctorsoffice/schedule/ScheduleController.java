package com.doctorsoffice.schedule;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/getSchedule")
    public List<ScheduleDto> getSchedule(@RequestParam Long doctorId) {
        try {
            final List<Schedule> schedules = scheduleService.getSchedules(doctorId);

            return scheduleMapper.toDto(schedules);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //update schedule dla doktor id i listy scheduleDto (z listy scheduledto, potrzebujemy tylko 4 danych (od, do, interval, dayofweek)


}
