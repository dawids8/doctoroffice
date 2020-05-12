package com.doctorsoffice.patient;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public Patient get(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));
    }

//    @Transactional(readOnly = true)
//    public Patient getByPesel(String pesel) {
//        return patientRepository.findPatientByPesel(pesel)
//                .orElseThrow(() -> new NoSuchElementException("There is no patient with such pesel"));
//    }

    @Transactional(readOnly = true)
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
