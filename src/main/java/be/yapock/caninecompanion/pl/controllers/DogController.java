package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog")
public class DogController {
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    /**
     * Updates the information of a dog with the specified ID.
     *
     * @param form The updated information of the dog as a {@link DogCreateForm} object.
     * @param id   The ID of the dog to be updated.
     */
    @PutMapping("/{id}")
    public void update(@RequestBody DogCreateForm form, @PathVariable long id){
        dogService.update(form, id);
    }
}
