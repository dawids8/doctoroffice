package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import com.doctorsoffice.validation.ValidationException;
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

    @Transactional
    public Appointment reserve(Long appointmentId, Long patientId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.AVAILABLE,
                "Appointment is already reserved.");

        final Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));

        appointment.setPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment complete(CompleteAppointmentRequest completeAppointmentRequest) {
        final Appointment appointment = getAppointment(completeAppointmentRequest.getAppointmentId(), AppointmentStatus.BOOKED,
                "There is no option to complete appointment that wasn't reserved before");

        appointment.setDiagnosis(completeAppointmentRequest.getDiagnosis());
        appointment.setPrescription(completeAppointmentRequest.getPrescription());
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment setAsNotCompleted(Long appointmentId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.BOOKED,
                "Only reserved appointments will be completed");

        appointment.setAppointmentStatus(AppointmentStatus.NOT_COMPLETED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment cancel(Long appointmentId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.BOOKED,
                "There is no option to cancel appointment if it is not booked");

        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);

        return appointmentRepository.save(appointment);
    }

    private Appointment getAppointment(Long appointmentId, AppointmentStatus status, String message) {
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        if (!status.equals(appointment.getAppointmentStatus())) {
            throw new ValidationException(message);
        }

        return appointment;
    }

    @Transactional
    public void delete(Long appointmentId) {
        //czy status to available

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
