package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.VaccineService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }
    @DeleteMapping("/{id}")
    public void delete(long id){
        vaccineService.delete(id);
    }
}
