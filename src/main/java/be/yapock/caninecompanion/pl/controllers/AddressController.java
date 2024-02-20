package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AddressService;
import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
import be.yapock.caninecompanion.pl.models.address.AddressFullDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Creates a new address based on the provided AddressForm.
     *
     * @param form The AddressForm object containing the details of the address to be created.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @PostMapping
    public void createAddress(@RequestBody AddressForm form) {
        addressService.create(form);
    }

    /**
     * Deletes an address with the specified ID.
     *
     * @param id The ID of the address to be deleted.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.delete(id);
    }

    /**
     * Retrieves the address for a given person ID.
     *
     * @param personId The ID of the person.
     * @return A ResponseEntity containing the address details in the AddressFullDto format. Returns HttpStatus.OK if the address is found, or HttpStatus.NOT_FOUND if the address
     * is not found.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_HELPER')")
    @GetMapping("/{personId}")
    public ResponseEntity<AddressFullDto> getAddressByPersonId(@PathVariable long personId) {
        return ResponseEntity.ok(AddressFullDto.fromEntity(addressService.getOneByPersonId(personId)));
    }
}
