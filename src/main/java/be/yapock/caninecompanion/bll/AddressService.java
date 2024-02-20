package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
import be.yapock.caninecompanion.pl.models.address.AddressFullDto;

public interface AddressService {
    void create(AddressForm form);
    Address getOneByPersonId(long id);
}
