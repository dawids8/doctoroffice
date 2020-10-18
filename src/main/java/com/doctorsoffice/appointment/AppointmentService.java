package com.doctorsoffice.appointment;

import com.doctorsoffice.common.InputDataValidator;
import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorRepository;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientRepository;
import com.doctorsoffice.schedule.Schedule;
import com.doctorsoffice.schedule.ScheduleRepository;
import com.doctorsoffice.user.User;
import com.doctorsoffice.user.UserRepository;
import com.doctorsoffice.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
                              PatientRepository patientRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void generateAppointments(GenerateAppointmentsRequest generateAppointmentsRequest) {
        InputDataValidator.validateDates(generateAppointmentsRequest.getStartDate(), generateAppointmentsRequest.getEndDate());

        final Doctor doctor = userRepository.findByUsername(generateAppointmentsRequest.getDoctorUsername())
                .orElseThrow(() -> new NoSuchElementException("There is no user with such username"))
                .getDoctor();

        List<Schedule> doctorSchedule = scheduleRepository.findAllByDoctor(doctor);

        int startDay = generateAppointmentsRequest.getStartDate().getDayOfYear();
        int endDay = generateAppointmentsRequest.getEndDate().getDayOfYear();

        for (int i = startDay; i <= endDay; i++) {
            LocalDate day = LocalDate.ofYearDay(generateAppointmentsRequest.getStartDate().getYear(), i);
            Schedule daySchedule = doctorSchedule.get(day.getDayOfWeek().getValue() - 1);

            long workDuration = Duration.between(daySchedule.getStart(), daySchedule.getFinish()).toMinutes();
            long appointmentDurationWithInterval = daySchedule.getAppointmentDuration() + daySchedule.getIntervalMinutes();
            long numberOfAppointmentsInADay = workDuration/appointmentDurationWithInterval;

            LocalTime appointmentStartTimeIndex = daySchedule.getStart();
            for (int j = 0; j < numberOfAppointmentsInADay; j++) {

                LocalDateTime startTime = LocalDateTime.of(day, appointmentStartTimeIndex);
                appointmentStartTimeIndex = appointmentStartTimeIndex.plusMinutes(daySchedule.getAppointmentDuration());
                LocalDateTime endTime = LocalDateTime.of(day, appointmentStartTimeIndex);

                Appointment appointment = new Appointment(startTime, endTime, doctor, AppointmentStatus.AVAILABLE);

                this.appointmentRepository.save(appointment);

                appointmentStartTimeIndex = appointmentStartTimeIndex.plusMinutes(daySchedule.getIntervalMinutes());
            }
        }
    }

    @Transactional
    public Appointment create(CreateAppointmentRequest createAppointmentRequest) {
        final LocalDateTime startDate = createAppointmentRequest.getStartDate();
        final LocalDateTime endDate = createAppointmentRequest.getEndDate();
        InputDataValidator.validateDates(createAppointmentRequest.getStartDate(), createAppointmentRequest.getEndDate());

        final Long doctorId = createAppointmentRequest.getDoctorId();
        final Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("There is no doctor with such id"));

        return appointmentRepository.save(new Appointment(startDate, endDate, doctor, AppointmentStatus.AVAILABLE));
    }

    @Transactional
    public Appointment reserve(Long appointmentId, Long patientId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.AVAILABLE,
                "Appointment is already reserved.");

        final Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("There is no patient with such id"));

        appointment.setPatient(patient);
        appointment.setAppointmentStatus(AppointmentStatus.RESERVED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment complete(CompleteAppointmentRequest completeAppointmentRequest) {
        final Appointment appointment = getAppointment(completeAppointmentRequest.getAppointmentId(), AppointmentStatus.RESERVED,
                "There is no option to complete appointment that wasn't reserved before");

        appointment.setDiagnosis(completeAppointmentRequest.getDiagnosis());
        appointment.setPrescription(completeAppointmentRequest.getPrescription());
        appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment setAsNotCompleted(Long appointmentId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.RESERVED,
                "Only reserved appointments will be not completed");

        appointment.setAppointmentStatus(AppointmentStatus.NOT_COMPLETED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment cancel(Long appointmentId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.RESERVED,
                "There is no option to cancel appointment if it is not reserved");

        appointment.setAppointmentStatus(AppointmentStatus.CANCELED);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment resignationByPatient(Long appointmentId) {
        final Appointment appointment = getAppointment(appointmentId, AppointmentStatus.RESERVED,
                "There is no option to cancel appointment if it is not reserved");

        appointment.setPatient(null);
        appointment.setAppointmentStatus(AppointmentStatus.AVAILABLE);

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
        final Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("There is no appointment with such id"));

        if (!appointment.getAppointmentStatus().equals(AppointmentStatus.AVAILABLE)) {
            throw new ValidationException("You can only delete appointment if status is AVAILABLE");
        }

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
    public List<Appointment> getAllByDoctorUsername(String username) {
        final Doctor doctor = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User name not exist"))
                .getDoctor();

        if (doctor == null) {
            throw new ValidationException("User is not a doctor");
        }

        return appointmentRepository.findAllByDoctorId(doctor.getId());
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAllByPatientId(Long id) {
        return appointmentRepository.findAllByPatientId(id);
    }
}
