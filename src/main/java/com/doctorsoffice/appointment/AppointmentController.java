package com.doctorsoffice.appointment;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // Obsłużyc wyjątek jak w delete (appointmentService -> logika)
    @GetMapping("/get")
    public AppointmentDto get(@RequestParam Long id) {
        final Appointment appointment = appointmentService.get(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no appointemnt with such id");
                });

        return appointmentMapper.toDto(appointment);
    }
}

/*
OPTIONALE

public boolean optionalExample(Long appointmentId) {
        Appointment appointment = appointmentService.get(appointmentId)
                .orElseThrow(NoSuchElementException::new);

        //tworzenie z obiektu, null spowoduje rzucenie wyjatku
        Optional.of(new Appointment());

        //tworzenie pustego optional
        Optional.empty();

        //tworzenie optional za pomoca obiektu lub null
        Optional.ofNullable(null);
        Optional.ofNullable(new Appointment());


        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime appointmentDate = appointment.getDate();

        return appointmentDate.isBefore(now);
    }
*/