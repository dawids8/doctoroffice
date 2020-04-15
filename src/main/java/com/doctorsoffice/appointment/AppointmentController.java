package com.doctorsoffice.appointment;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Data
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentMapper appointmentMapper, AppointmentService appointmentService) {
        this.appointmentMapper = appointmentMapper;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public AppointmentDto create(@RequestBody AppointmentDto appointmentDto) { // dodawanie pojedynczej wizyty
        final Appointment appointment = appointmentMapper.fromDto(appointmentDto);

        try {
            appointmentService.reserve(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return appointmentMapper.toDto(appointment);
    }

    // reserve appID i patID do rezerwowania wizyty przez pacjenta + walidacje

//    @PutMapping("/fill")
//    public AppointmentDto fill(@RequestBody AppointmentDto appointmentDto) {
//     z appointmentDto wyciagam ID i wrzucam do metody na serwisie do jej uzupełnienia
//    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        try {
            appointmentService.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/get")
    public AppointmentDto get(@RequestParam Long id) {
        try {
            final Appointment appointment = appointmentService.get(id);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<AppointmentDto> getAll() {
        final List<Appointment> appointments = appointmentService.getAll();
        return appointmentMapper.toDto(appointments);
    }

    @GetMapping("/getAllByDoctorId")
    public List<AppointmentDto> getAllByDoctorId(@RequestParam Long id) {
        final List<Appointment> appointments = appointmentService.getAllByDoctorId(id);
        return appointmentMapper.toDto(appointments);
    }

    @GetMapping("/getAllByPatientId")
    public List<AppointmentDto> getAllByPatientrId(@RequestParam Long id) {
        final List<Appointment> appointments = appointmentService.getAllByPatientId(id);
        return appointmentMapper.toDto(appointments);
    }
}
