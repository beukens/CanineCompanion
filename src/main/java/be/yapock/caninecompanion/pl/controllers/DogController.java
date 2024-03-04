package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.dog.DogFullDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * Creates a new dog with the provided information.
     *
     * @param form The form containing the information of the dog to be created.
     */
    @PostMapping("/")
    public void create(@RequestBody DogCreateForm form) {
        dogService.create(form);
    }

    /**
     * Retrieves a dog by its ID.
     *
     * @param id The ID of the dog to retrieve.
     * @return The dog with the specified ID.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER') || hasRole('ROLE_INTERN')")
    public ResponseEntity<DogFullDTO> getDogById(@PathVariable Long id) {
        return ResponseEntity.ok(DogFullDTO.fromEntity(dogService.getDogById(id)));
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
