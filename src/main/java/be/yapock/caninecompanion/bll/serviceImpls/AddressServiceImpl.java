package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AddressService;
import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.AddressRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
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
        Person person = personRepository.findById(form.personId())
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable"));
        Address address = Address.builder()
                .street(form.street())
                .number(form.number())
                .box(form.box())
                .zip(form.zip())
                .city(form.city())
                .country(form.country())
                .person(person)
                .build();
        addressRepository.save(address);
    }

    /**
     * Deletes an address with the given ID.
     *
     * @param id the ID of the address to delete
     */
    @Override
    public void delete(long id) {
        addressRepository.deleteById(id);
    }
}
