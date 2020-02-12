package com.doctorsoffice.treatments;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Treatment {

    @Id
    @GeneratedValue
    @Column(name = "TREATMENT_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SPECIFICATION")
    private String specification;

    @Column(name = "DATE")
    private LocalDateTime date;

    public Treatment() {
    }

    public Treatment(Long id, String title, String specification, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.specification = specification;
        this.date = date;
    }
}
