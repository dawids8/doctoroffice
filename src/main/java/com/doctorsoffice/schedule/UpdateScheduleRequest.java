package com.doctorsoffice.schedule;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class UpdateScheduleRequest {

    private String username;
    private List<ScheduleDto> schedules;

}
