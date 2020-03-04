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
    public AppointmentDto create(@RequestBody AppointmentDto appointmentDto) {
        final Appointment appointment = appointmentMapper.fromDto(appointmentDto);

        try{
            appointmentService.create(appointment);
        } catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return appointmentMapper.toDto(appointment);
    }

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
            final AppointmentDto appointmentDto = appointmentMapper.toDto(appointment);
            System.out.println(appointment);
            return appointmentDto;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<AppointmentDto> getAll() {
        final List<Appointment> appointments = appointmentService.getAll();
        return appointmentMapper.toDto(appointments);
    }
}
