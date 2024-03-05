package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineShortDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Creates a new vaccine record.
     *
     * @param form the VaccineForm object containing the details of the vaccine
     */
    @PostMapping()
    public void create(@RequestBody VaccineForm form){
        vaccineService.create(form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccineDTO> getOne(long id){
        return ResponseEntity.ok(VaccineDTO.fromEntity(vaccineService.getOne(id)));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<VaccineShortDTO>> getAllByDog(long id){
        return ResponseEntity.ok(vaccineService.gateAllByDog(id).stream()
                .map(VaccineShortDTO::fromEntity)
                .toList());
    }
}
