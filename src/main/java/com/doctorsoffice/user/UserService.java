package com.doctorsoffice.user;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.doctor.MedicalSpecialization;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import com.doctorsoffice.validation.ValidationException;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Data
public class UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public UserService(UserRepository userRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void create(User user, MedicalSpecialization medicalSpecialization) {
        validateUser(user);

        switch (user.getUserRole()) {
            case DOCTOR:
                final Doctor doctor = Doctor.builder()
                        .medicalSpecialization(medicalSpecialization)
                        .build();
                doctor.setUser(user);
                doctorRepository.save(doctor);
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

    @Transactional
    public void login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                System.out.println("Correct");
            } else {
                System.out.println("Wrong password");
            }

        } else {
            System.out.println("This username doesn't exist");
        }
    }
}
