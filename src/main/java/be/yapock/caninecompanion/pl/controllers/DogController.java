package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.DogService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import be.yapock.caninecompanion.pl.models.dog.DogFullDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import be.yapock.caninecompanion.pl.models.dog.DogShortDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
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
     * Deletes a dog with the specified ID.
     *
     * @param id the ID of the dog to be deleted
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    public void delete(@PathVariable long id){
        dogService.delete(id);
    }

    /**
     * Creates a new dog with the provided information.
     *
     * @param form The form containing the information of the dog to be created.
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
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
     * Searches for dogs based on the provided search criteria.
     *
     * @param form the search criteria form
     * @return a ResponseEntity containing a list of DogShortDTO objects
     */
    @PostMapping("/search")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER') || hasRole('ROLE_INTERN')")
    public ResponseEntity<List<DogShortDTO>> search(@RequestBody DogSearchForm form){
        return ResponseEntity.ok(dogService.search(form).stream()
                .map(DogShortDTO::fromEntity)
                .toList());
    }

    /**
     * Finds all dogs owned by the specified owner.
     *
     * @param id the ID of the owner
     * @return a ResponseEntity containing a list of DogShortDTO objects
     */
    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER') || hasRole('ROLE_INTERN') || #id== authentication.principal.id")
    public ResponseEntity<List<DogFullDTO>> findAllByOwner(@PathVariable @P("id") long id){
        return ResponseEntity.ok(dogService.findAllByOwner(id).stream()
                .map(DogFullDTO::fromEntity)
                .toList());
    }

    /**
     * Updates the information of a dog with the specified ID.
     *
     * @param form The updated information of the dog as a {@link DogCreateForm} object.
     * @param id   The ID of the dog to be updated.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    public void update(@RequestBody DogCreateForm form, @PathVariable long id){
        dogService.update(form, id);
    }
}
