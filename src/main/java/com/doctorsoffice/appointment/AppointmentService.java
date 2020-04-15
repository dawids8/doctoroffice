package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void reserve(Appointment appointment) {
//        boolean isDateAfterNow = appointment.getDate().isAfter(LocalDateTime.now());
        //2. Dodac metode generujaca wizyty na podstawie id doktora, danych z DEFUALT_SCHEDULE i danych z zewnatrz na jaki okres.
        //5. Warto pomyslec o alternatywnej metodzie na generowanie wizyt. Wg. dokladnych danych.

        final Long doctorId = appointment.getDoctor().getId();
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));

        final Long patientID = appointment.getPatient().getId();
        final Patient patient = patientRepository.findById(patientID)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        appointmentRepository.save(appointment);
    }

    public void complete(Long appointmentId, String diagnosis, String prescription) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        appointment.setDiagnosis(diagnosis);
        appointment.setPrescription(prescription);
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);

        appointmentRepository.save(appointment);
    }

    public void cancel(Long appointmentId) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));


        appointmentRepository.save(appointment);
    }

    @Transactional
    public void delete(Long appointmentId) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        appointmentRepository.delete(appointment);
    }

    @Transactional(readOnly = true)
    public Appointment get(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllByDoctorId(Long id) {
        return appointmentRepository.findAllByDoctorId(id);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllByPatientId(Long id) {
        return appointmentRepository.findAllByPatientId(id);
    }
}
