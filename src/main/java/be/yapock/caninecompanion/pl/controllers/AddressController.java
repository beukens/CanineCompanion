package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AddressService;
import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
import be.yapock.caninecompanion.pl.models.address.AddressFullDto;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public void createAddress(@RequestBody AddressForm form) {
        addressService.create(form);
    }

    /**
     * Retrieves the address for a given person ID.
     *
     * @param personId The ID of the person.
     * @return A ResponseEntity containing the address details in the AddressFullDto format. Returns HttpStatus.OK if the address is found, or HttpStatus.NOT_FOUND if the address
     * is not found.
     */
    @GetMapping("/{personId}")
    public ResponseEntity<AddressFullDto> getAddressByPersonId(@PathVariable long personId) {
        return ResponseEntity.ok(AddressFullDto.fromEntity(addressService.getOneByPersonId(personId)));
    }
}
