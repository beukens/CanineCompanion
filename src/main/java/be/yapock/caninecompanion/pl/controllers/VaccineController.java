package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
