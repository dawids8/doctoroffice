package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Appointment create(CreateAppointmentRequest createAppointmentRequest) {
        final LocalDateTime appointmentDate = createAppointmentRequest.getAppointmentDate();
        final boolean isFromPast = appointmentDate.isBefore(LocalDateTime.now());

        if(isFromPast) {
            throw new RuntimeException("There is no way to create appointment in past.");
        }

        final Long doctorId = createAppointmentRequest.getDoctorId();
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));

        final Appointment appointment = new Appointment();
        appointment.setDate(appointmentDate);
        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus(AppointmentStatus.AVAILABLE);

        return appointmentRepository.save(appointment);
    }

    public void complete(Long appointmentId, String diagnosis, String prescription) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        appointment.setDiagnosis(diagnosis);
        appointment.setPrescription(prescription);
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);

        appointmentRepository.save(appointment);
    }

    //todo
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
