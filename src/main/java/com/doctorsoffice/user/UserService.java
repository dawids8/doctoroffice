package com.doctorsoffice.user;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.doctor.MedicalSpecialization;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import com.doctorsoffice.schedule.Schedule;
import com.doctorsoffice.schedule.ScheduleRepository;
import com.doctorsoffice.schedule.WeekDay;
import com.doctorsoffice.validation.ValidationException;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ScheduleRepository scheduleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, DoctorRepository doctorRepository, PatientRepository patientRepository,
                       ScheduleRepository scheduleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.scheduleRepository = scheduleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void create(User user, MedicalSpecialization medicalSpecialization) {
        validateUser(user);
        final String password = user.getPassword();
        final String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);

        switch (user.getUserRole()) {
            case DOCTOR:
                final Doctor doctor = Doctor.builder()
                        .medicalSpecialization(medicalSpecialization)
                        .build();
                doctor.setUser(user);

                doctorRepository.save(doctor);

                final List<Schedule> schedules = prepareDefaultSchedules(doctor);
                doctor.setSchedules(schedules);

                scheduleRepository.saveAll(schedules);
                break;
            case PATIENT:
                final Patient patient = new Patient();
                patient.setUser(user);
                patientRepository.save(patient);
                break;
            default:
                throw new ValidationException("User role is invalid.");
        }

        userRepository.save(user);
    }

    private List<Schedule> prepareDefaultSchedules(Doctor doctor) {
        final List<Schedule> schedules = new ArrayList<>();

        schedules.add(new Schedule(null, null, null, null, WeekDay.MONDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.TUESDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.WEDNESDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.THURSDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.FRIDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.SATURDAY, doctor));
        schedules.add(new Schedule(null, null, null, null, WeekDay.SUNDAY, doctor));

        return schedules;
    }

    private void validateUser(User user) {
        final List<Boolean> list = Arrays.asList(userRepository.existsPatientByPesel(user.getPesel()),
                userRepository.existsPatientByPhoneNumber(user.getPhoneNumber()),
                userRepository.existsPatientByEmail(user.getEmail()));

        if (list.get(0)) {
            throw new DataIntegrityViolationException("Pesel already in use");
        } else if (list.get(1)) {
            throw new DataIntegrityViolationException("Phone number already in use");
        } else if (list.get(2)) {
            throw new DataIntegrityViolationException("Email already in use");
        }
    }

}
