package com.doctorsoffice.appointment;

import com.doctorsoffice.validation.ValidationException;
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
    public AppointmentDto create(@RequestBody CreateAppointmentRequest createAppointmentRequest) {
        try {
            final Appointment appointment = this.appointmentService.create(createAppointmentRequest);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/reserve")
    public AppointmentDto reserve(@RequestParam Long appointmentId, @RequestParam Long patientId) {
        try {
            final Appointment appointment = this.appointmentService.reserve(appointmentId, patientId);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/complete")
    public AppointmentDto complete(@RequestBody CompleteAppointmentRequest completeAppointmentRequest) {
        try {
            final Appointment appointment = this.appointmentService.complete(completeAppointmentRequest);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/setAsNotCompleted")
    public AppointmentDto setAsNotCompleted(@RequestParam Long appointmentId) {
        try {
            final Appointment appointment = this.appointmentService.setAsNotCompleted(appointmentId);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
    }

    @PutMapping("/cancel")
    public AppointmentDto cancel(@RequestParam Long appointmentId) {
        try {
            final Appointment appointment = this.appointmentService.cancel(appointmentId);
            return appointmentMapper.toDto(appointment);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (ValidationException e) {
            throw new ResponseStatusException(e.getHttpStatus(), e.getMessage());
        }
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
