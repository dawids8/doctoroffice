package com.doctorsoffice.treatments;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public void create(Treatment treatment) {
        treatmentRepository.save(treatment);
    }

    public void delete(Treatment treatment) {
        treatmentRepository.delete(treatment);
    }

    public Optional<Treatment> get(Long id) {
        return treatmentRepository.findById(id);
    }

}
