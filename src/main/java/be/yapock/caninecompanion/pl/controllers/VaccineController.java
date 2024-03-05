package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineUpdateForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PutMapping("/{id}")
    public void update(@RequestBody VaccineUpdateForm form, @PathVariable long id){
        vaccineService.update(form, id);
    }
}
