package com.doctorsoffice.treatments;

import org.springframework.stereotype.Component;

@Component
public class TreatmentMapper {

    public TreatmentDto toDto(Treatment treatment) {
        return new TreatmentDto(treatment.getId(), treatment.getTitle(), treatment.getSpecification(), treatment.getDate());
    }

    public Treatment fromDto(TreatmentDto treatmentDto) {
        return new Treatment(treatmentDto.getId(), treatmentDto.getTitle(), treatmentDto.getSpecification(),
                treatmentDto.getDate());
    }
}
