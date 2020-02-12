package com.doctorsoffice.treatments;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Data
@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    private final TreatmentMapper treatmentMapper;
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentMapper treatmentMapper, TreatmentService treatmentService) {
        this.treatmentMapper = treatmentMapper;
        this.treatmentService = treatmentService;
    }

    @PostMapping("/create")
    public TreatmentDto create(@RequestBody TreatmentDto treatmentDto) {
        final Treatment treatment = treatmentMapper.fromDto(treatmentDto);
        treatmentService.create(treatment);

        return treatmentMapper.toDto(treatment);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        final Treatment treatment = treatmentService.get(id)
                .get();

        treatmentService.delete(treatment);
    }

    @GetMapping("/get")
    public TreatmentDto get(@RequestParam Long id) {
        final Treatment treatment = treatmentService.get(id)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no treatment with such id");
                });

        return treatmentMapper.toDto(treatment);
    }

}
