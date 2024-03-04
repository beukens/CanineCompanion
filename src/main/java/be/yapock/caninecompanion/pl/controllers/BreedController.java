package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
