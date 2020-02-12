package com.doctorsoffice.appointment;

import com.doctorsoffice.doctor.Doctor;
import com.doctorsoffice.doctor.DoctorDto;
import com.doctorsoffice.doctor.DoctorMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final DoctorMapper doctorMapper;

    public AppointmentMapper(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    public AppointmentDto toDto(Appointment appointment) {
        final DoctorDto doctorDto = doctorMapper.toDto(appointment.getDoctor());

        return new AppointmentDto(appointment.getId(), appointment.getDate(), appointment.getDiagnosis(),
                appointment.getPrescription(), doctorDto);
    }

    public Appointment fromDto(AppointmentDto appointmentDto) {
        final Doctor doctor = doctorMapper.fromDto(appointmentDto.getDoctorDto());

        return new Appointment(appointmentDto.getId(), appointmentDto.getDate(), appointmentDto.getDiagnosis(),
                appointmentDto.getPrescription(), doctor);
    }
}
