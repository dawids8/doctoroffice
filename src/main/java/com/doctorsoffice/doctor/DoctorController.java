package com.doctorsoffice.doctor;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping("/get")
    public DoctorDto get(@RequestParam Long id) {
        try {
            final Doctor doctor = doctorService.get(id);
            System.out.println(doctor);
            return doctorMapper.toDto(doctor);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<DoctorDto> getAll() {
        final List<Doctor> doctorsList = doctorService.getAll();
        return doctorMapper.toDto(doctorsList);
    }

    @GetMapping("/getByMedicalSpecialization")
    public List<DoctorDto> getAllByMedicalSpecialization(@RequestParam MedicalSpecialization medicalSpecialization) {
        final List<Doctor> doctorsList = doctorService.getDoctorsWithSpecifiedSpecialization(medicalSpecialization);
        return doctorMapper.toDto(doctorsList);
    }
}
