package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.PersonService;
import be.yapock.caninecompanion.pl.models.PersonForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
}
