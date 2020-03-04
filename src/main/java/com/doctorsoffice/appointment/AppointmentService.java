package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public void create(Appointment appointment) {
        final Long doctorId = appointment.getDoctor().getId();
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));

        final Long patientID = appointment.getPatient().getId();
        final Patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appointmentRepository.save(appointment);
    }

    public void delete(Long appointmentId) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        appointmentRepository.delete(appointment);
    }

    public Appointment get(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

}
