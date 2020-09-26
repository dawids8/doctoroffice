package com.doctorsoffice.schedule;

import com.doctorsoffice.doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final DoctorService doctorService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleController(DoctorService doctorService, ScheduleMapper scheduleMapper) {
        this.doctorService = doctorService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/getSchedule")
    public List<ScheduleDto> getSchedule(@RequestParam String username) {
        try {
            final List<Schedule> schedules = doctorService.getSchedules(username);

            return scheduleMapper.toDto(schedules);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/update")
    public List<ScheduleDto> update(@RequestBody UpdateScheduleRequest updateScheduleRequest) {
        try {
            List<Schedule> result = doctorService.update(updateScheduleRequest);
            return scheduleMapper.toDto(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }
}
