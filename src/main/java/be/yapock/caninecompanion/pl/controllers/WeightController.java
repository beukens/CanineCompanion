package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.pl.models.weight.WeightForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weight")
public class WeightController {
    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    /**
     * Creates a new weight entry using the provided WeightForm.
     *
     * @param form the WeightForm object containing the dogId and weight information
     */
    @PostMapping("/")
    public void createWeight(@RequestBody WeightForm form) {
        weightService.create(form);
    }
}
