package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AddressService;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
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
     * Deletes an address with the specified ID.
     *
     * @param id The ID of the address to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id) {
        addressService.delete(id);
    }
}
