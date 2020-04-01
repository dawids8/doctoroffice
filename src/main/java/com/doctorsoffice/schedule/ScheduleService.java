package com.doctorsoffice.schedule;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


}
