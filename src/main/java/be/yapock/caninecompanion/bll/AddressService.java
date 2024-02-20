package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.address.AddressForm;

public interface AddressService {
    void create(AddressForm form);
    void delete(long id);
}
