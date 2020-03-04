package com.doctorsoffice.doctor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void create(Doctor doctor) {
        doctorRepository.save(doctor);
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
