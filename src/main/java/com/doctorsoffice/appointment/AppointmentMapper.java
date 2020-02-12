package com.doctorsoffice.appointment;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(appointment.getId(), appointment.getDate(), appointment.getDiagnosis(),
                appointment.getPrescription());
    }

    public Appointment fromDto(AppointmentDto appointmentDto) {
        return new Appointment(appointmentDto.getId(), appointmentDto.getDate(), appointmentDto.getDiagnosis(),
                appointmentDto.getPrescription());
    }
}





/*
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
                user.getDateOfBirth(), user.getEmail(), user.getPhoneNumber());
    }

    public User fromDto(UserDto userDto) {  // po co dajemy id skoro otrzymujemy od fronta
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getFirstname(),
                userDto.getLastname(), userDto.getDateOfBirth(), userDto.getEmail(), userDto.getPhoneNumber());
    }
 */