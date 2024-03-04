package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * Creates a new dog based on the provided form.
     *
     * @param form the dog creation form
     */
    @PostMapping("/")
    public void createDog(@RequestBody DogCreateForm form) {
        dogService.create(form);
    }
}
