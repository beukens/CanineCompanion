package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AddressService;
import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.AddressRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
import be.yapock.caninecompanion.pl.models.address.AddressFullDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public AddressServiceImpl(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    /**
     * Create a new address using the given form.
     *
     * @param form the address form containing the necessary data to create the address
     * @throws IllegalArgumentException if the form is null or if the user is not found
     */
    @Override
    public void create(AddressForm form) {
        if (form==null) throw new IllegalArgumentException("Le formulaire ne peut être null");
        if (!addressRepository.existsByCityAndStreetAndNumberAndBox(form.city(), form.street(), form.number(), form.box())) {
            Address address = Address.builder()
                    .street(form.street())
                    .number(form.number())
                    .box(form.box())
                    .zip(form.zip())
                    .city(form.city())
                    .country(form.country())
                    .build();
            addressRepository.save(address);
        }
    }

    /**
     * Retrieves the address of a person by their ID.
     *
     * @param id the ID of the person
     * @return the address of the person with the specified ID
     * @throws EntityNotFoundException if the person is not found
     */
    @Override
    public Address getOneById(long id) {
        return addressRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Adresse introuvable"));
    }

    /**
     * Deletes an address with the given ID.
     *
     * @param id the ID of the address to delete
     */
    @Override
    public void delete(long id) {
        Address address = addressRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Adresse introuvable"));
        address.setDeleted(true);
        addressRepository.save(address);
    }
}
