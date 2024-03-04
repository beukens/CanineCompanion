package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breed")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(BreedDTO.fromEntity(breedService.getOne(id)));
    }

    /**
     * Creates a new Breed record.
     *
     * @param form The BreedForm object containing the values for the new Breed record.
     */
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    public void create(@RequestBody BreedForm form){
        breedService.create(form);
    }

    /**
     * Deletes a Breed by its ID.
     *
     * @param id The ID of the Breed to be deleted.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        breedService.delete(id);
    }

    /**
     * Retrieves a list of all breeds.
     *
     * @return A ResponseEntity object containing a list of BreedDTO objects representing the breeds.
     */
    @GetMapping
    public ResponseEntity<List<BreedDTO>> getAll(){
        return ResponseEntity.ok(breedService.getAll().stream()
                .map(BreedDTO::fromEntity)
                .toList());
    }
}
