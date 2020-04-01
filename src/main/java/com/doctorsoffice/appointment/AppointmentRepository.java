package com.doctorsoffice.appointment;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorId(Long id);

    List<Appointment> findAllByPatientId(Long id);

    @Override
    @EntityGraph(value = "Appointment.detail", type = EntityGraph.EntityGraphType.LOAD)
    List<Appointment> findAll();
}
