package com.doctorsoffice.schedule;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private ScheduleMapper scheduleMapper;
    private DoctorService doctorService;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, DoctorService doctorService) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.doctorService = doctorService;
    }

    @Transactional
    public void update(UpdateScheduleRequest updateScheduleRequests) {
//        List<Schedule> schedules = updateScheduleRequests.getSchedules();
//
//        for (Schedule s : schedules) {
//            scheduleRepository.save(s);
//        }
    }

    @Transactional(readOnly = true)
    public List<Schedule> getSchedules(Long doctorId) {
        final Doctor doctor = doctorService.get(doctorId);
        return scheduleRepository.findAllByDoctor(doctor);
    }
}
