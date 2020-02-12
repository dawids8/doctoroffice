package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public void create(Appointment appointment) {
        final Long doctorId = appointment.getDoctor().getId();
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));

        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);
    }

    public void delete(Long appointmentId) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        appointmentRepository.delete(appointment);
    }

    public Optional<Appointment> get(Long id) {
        return appointmentRepository.findById(id);
    }

}
