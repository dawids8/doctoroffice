package com.doctorsoffice.doctor;

import com.doctorsoffice.schedule.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;

    public DoctorService(DoctorRepository doctorRepository, ScheduleRepository scheduleRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public List<Schedule> update(UpdateScheduleRequest updateScheduleRequests) {
        final Doctor doctor = get(updateScheduleRequests.getDoctorId());
        final List<Schedule> actualSchedules = doctor.getSchedules();
        final List<ScheduleDto> scheduleRequestsSchedules = updateScheduleRequests.getSchedules();

        if (actualSchedules == null || actualSchedules.isEmpty()) {
            throw new RuntimeException("Doctor doesn't have any schedules");
        }

        if (actualSchedules.size() != 7) {
            throw new RuntimeException("Critical exception. Doctor doesn't have seven schedules.");
        }

        for (Schedule actualSchedule : actualSchedules) {
            for (ScheduleDto requestSchedule : scheduleRequestsSchedules) {
                if (actualSchedule.getWeekDay().equals(requestSchedule.getWeekDay())) {
                    actualSchedule.setStart(requestSchedule.getStart());
                    actualSchedule.setFinish(requestSchedule.getFinish());
                    actualSchedule.setAppointmentDuration(requestSchedule.getAppointmentDuration());
                    actualSchedule.setIntervalMinutes(requestSchedule.getIntervalMinutes());
                    this.scheduleRepository.save(actualSchedule);
                }
            }
        }

        return actualSchedules;
    }

    @Transactional(readOnly = true)
    public List<Schedule> getSchedules(Long doctorId) {
        final Doctor doctor = get(doctorId);
        return scheduleRepository.findAllByDoctor(doctor);
    }

    @Transactional(readOnly = true)
    public Doctor get(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Doctor> getDoctorsWithSpecifiedSpecialization(MedicalSpecialization medicalSpecialization) {
        return doctorRepository.findDoctorByMedicalSpecialization(medicalSpecialization);
    }
}
