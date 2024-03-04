package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * Deletes a dog with the specified ID.
     *
     * @param id the ID of the dog to be deleted
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        dogService.delete(id);
    }
}
