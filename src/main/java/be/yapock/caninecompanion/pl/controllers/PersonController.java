package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.PersonService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.pl.models.person.PersonForm;
import be.yapock.caninecompanion.pl.models.person.PersonFullDTO;
import be.yapock.caninecompanion.pl.models.person.PersonSearchForm;
import be.yapock.caninecompanion.pl.models.person.PersonShortDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a new person by calling the create method of the PersonService.
     *
     * @param form the PersonForm object containing the details of the person to be created
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @PostMapping
    public void create(@RequestBody PersonForm form){
        personService.create(form);
    }

    /**
     * Retrieves a specific Person by their ID.
     *
     * @param id the ID of the Person to retrieve
     * @param authentication the authentication object representing the current user
     * @return the Person object corresponding to the given ID
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<PersonFullDTO> getOne(@PathVariable long id, Authentication authentication) {
        return ResponseEntity.ok(PersonFullDTO.fromEntity(personService.getOne(id, authentication)));
    }

    /**
     * Retrieves all persons with pagination.
     *
     * @param pageable the pagination information
     * @return a ResponseEntity containing a Page of PersonShortDTO objects
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @GetMapping
    public ResponseEntity<Page<PersonShortDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(personService.getAll(pageable)
                .map(PersonShortDTO::fromEntity));
    }

    /**
     * Updates a person with the given ID using the provided form and authentication information.
     * Requires the user to be authenticated.
     *
     * @param id the ID of the person to update
     * @param form the PersonForm object containing the updated details of the person
     * @param authentication the authentication object representing the current user
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody PersonForm form, Authentication authentication) {
        personService.update(id, form, authentication);
    }

    /**
     * Deletes a person with the specified ID.
     *
     * @param id the ID of the person to delete
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        personService.delete(id);
    }

    /**
     * Search for persons based on the provided search criteria.
     *
     * @param form the PersonSearchForm object containing the search criteria
     * @return a ResponseEntity containing a List of PersonShortDTO objects that match the search criteria
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @PostMapping("/search")
    public ResponseEntity<List<PersonShortDTO>> search(@RequestBody PersonSearchForm form) {
        return ResponseEntity.ok(personService.search(form)
                .stream()
                .map(PersonShortDTO::fromEntity)
                .collect(Collectors.toList()));
    }

}
