package com.doctorsoffice.patient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient get(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));
    }

    public Patient getByPesel(String pesel) {
        return patientRepository.findPatientByPesel(pesel)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such pesel"));
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
