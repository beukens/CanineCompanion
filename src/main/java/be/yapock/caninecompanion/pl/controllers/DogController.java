package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Searches for dogs based on the provided search criteria.
     *
     * @param form the search criteria form
     * @return a ResponseEntity containing a list of DogShortDTO objects
     */
    @PostMapping("/search")
    public ResponseEntity<List<DogShortDTO>> search(DogSearchForm form){
        return ResponseEntity.ok(dogService.search(form).stream()
                .map(DogShortDTO::fromEntity)
                .toList());
    }
}
