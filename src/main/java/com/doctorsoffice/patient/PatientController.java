package com.doctorsoffice.patient;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patient")
public class PatientController {


    private final PatientMapper patientMapper;
    private final PatientService patientService;

    public PatientController(PatientMapper patientMapper, PatientService patientService) {
        this.patientMapper = patientMapper;
        this.patientService = patientService;
    }

    @GetMapping("/get")
    public PatientDto get(@RequestParam Long id) {
        try {
            final Patient patient = patientService.get(id);
            return patientMapper.toDto(patient);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<PatientDto> getAll() {
        final List<Patient> patientList = patientService.getAll();
        return patientMapper.toDto(patientList);
    }

    @GetMapping("/getByPesel")
    public PatientDto getByPesel(@RequestParam String pesel) {
        final Patient patient = patientService.getByPesel(pesel);
        return patientMapper.toDto(patient);
    }
}
