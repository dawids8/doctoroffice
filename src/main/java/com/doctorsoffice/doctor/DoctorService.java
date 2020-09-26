package com.doctorsoffice.doctor;

import com.doctorsoffice.schedule.*;
import com.doctorsoffice.user.User;
import com.doctorsoffice.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Schedule> update(UpdateScheduleRequest updateScheduleRequests) {
        final Doctor doctor = get(updateScheduleRequests.getUsername());
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
    public List<Schedule> getSchedules(String username) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new);

        return scheduleRepository.findAllByDoctor(user.getDoctor());
    }

    @Transactional(readOnly = true)
    public Doctor get(String username) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new);

        return user.getDoctor();
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Doctor> getDoctorsWithSpecifiedSpecialization(MedicalSpecialization medicalSpecialization) {
        return doctorRepository.findDoctorByMedicalSpecialization(medicalSpecialization);
    }

    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such ID"));
    }
}
