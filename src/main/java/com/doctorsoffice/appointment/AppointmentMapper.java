package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorDto;
import com.doctorsoffice.doctor.DoctorMapper;
import com.doctorsoffice.patient.Patient;
import com.doctorsoffice.patient.PatientDto;
import com.doctorsoffice.patient.PatientMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public AppointmentMapper(DoctorMapper doctorMapper, PatientMapper patientMapper) {
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
    }

    public AppointmentDto toDto(Appointment appointment) {
        final DoctorDto doctorDto = doctorMapper.toDto(appointment.getDoctor());
        final PatientDto patientDto = patientMapper.toDto(appointment.getPatient());

        return new AppointmentDto(appointment.getId(), appointment.getDate(), appointment.getDiagnosis(),
                appointment.getPrescription(), doctorDto, patientDto);
    }

    public List<AppointmentDto> toDto(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Appointment fromDto(AppointmentDto appointmentDto) {
        final Doctor doctor = doctorMapper.fromDto(appointmentDto.getDoctorDto());
        final Patient patient = patientMapper.fromDto(appointmentDto.getPatientDto());

        return new Appointment(appointmentDto.getId(), appointmentDto.getDate(), appointmentDto.getDiagnosis(),
                appointmentDto.getPrescription(), doctor, patient);
    }
}
