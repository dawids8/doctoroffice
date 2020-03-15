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

    @Transactional
    public void create(Patient patient) {
        validatePatient(patient);
        patientRepository.save(patient);
    }

    private void validatePatient(Patient patient) {
        final List<Boolean> list = Arrays.asList(patientRepository.existsPatientByPesel(patient.getPesel()),
                patientRepository.existsPatientByPhoneNumber(patient.getPhoneNumber()),
                patientRepository.existsPatientByEmail(patient.getEmail()));

        if (list.get(0)) {
            throw new DataIntegrityViolationException("Pesel already in use");
        } else if (list.get(1)) {
            throw new DataIntegrityViolationException("Phone number already in use");
        } else if (list.get(2)) {
            throw new DataIntegrityViolationException("Email already in use");
        }
    }

    @Transactional(readOnly = true)
    public Patient get(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));
    }

    @Transactional(readOnly = true)
    public Patient getByPesel(String pesel) {
        return patientRepository.findPatientByPesel(pesel)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such pesel"));
    }

    @Transactional(readOnly = true)
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
