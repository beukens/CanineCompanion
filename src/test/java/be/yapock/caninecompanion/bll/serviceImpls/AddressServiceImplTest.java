package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Address;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.repositories.AddressRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.address.AddressForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private AddressServiceImpl addressService;

    private AddressForm addressForm;
    private Address address;
    private Person person;
    @BeforeEach
    void setUp() {
        person = Person.builder()
                .id(1L)
                .build();
        addressForm = new AddressForm("street", 1, "box", 1000, "city", "country", person.getId());
        address = Address.builder()
                .person(person)
                .box(addressForm.box())
                .city(addressForm.city())
                .country(addressForm.country())
                .zip(addressForm.zip())
                .number(addressForm.number())
                .id(1L)
                .street(addressForm.street())
                .build();
    }


    @Test
    void create_ok() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        when(addressRepository.save(any())).thenReturn(address);

        addressService.create(addressForm);

        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    void creat_ko_formIsNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> addressService.create(null));

        String expectedMessage = "Le formulaire ne peut être null";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_personNotFound(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> addressService.create(addressForm));

        String expectedMessage= "Utilisateur introuvable";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void delete_ok(){
        addressService.delete(anyLong());

        verify(addressRepository,times(1)).deleteById(anyLong());
    }
}