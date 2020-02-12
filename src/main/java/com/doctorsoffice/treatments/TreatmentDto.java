package com.doctorsoffice.treatments;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TreatmentDto {

    private Long id;
    private String title;
    private String specification;
    private LocalDateTime date;

    public TreatmentDto() {
    }

    public TreatmentDto(Long id, String title, String specification, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.specification = specification;
        this.date = date;
    }
}
