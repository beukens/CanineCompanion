package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import be.yapock.caninecompanion.pl.models.dog.DogUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @Mock
    private DogRepository dogRepository;
    @Mock
    private BreedRepository breedRepository;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private DogServiceImpl dogService;

    private DogCreateForm createForm;
    private Dog dog;
    private DogSearchForm searchForm;
    private DogUpdateForm updateForm;
    private Breed breed;
    private Person person;

    @BeforeEach
    void setUp(){
        createForm = new DogCreateForm("John", "Doe", LocalDate.now(), "Male", true,1L, 2L, 5L);
        dog = Dog.builder()
                .firstName(createForm.firstName())
                .lastName(createForm.lastName())
                .dateOfBirth(createForm.dateOfBirth())
                .sex(createForm.sex())
                .isSterilized(createForm.isSterilized())
                .build();
        updateForm= new DogUpdateForm(dog.getFirstName(),dog.getLastName(),dog.getDateOfBirth(),dog.getSex(),dog.isSterilized());
        searchForm= new DogSearchForm("");
        person = new Person();
        breed= new Breed();
    }

    @Test
    void create_ok() {
        when(breedRepository.findById(anyLong())).thenReturn(Optional.of(breed));
        when(personRepository.findById(anyLong())). thenReturn(Optional.of(person));
        when(dogRepository.save(any(Dog.class))).thenReturn(dog);


        dogService.create(createForm);

        verify(dogRepository, times(1)).save(any(Dog.class));
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> dogService.create(null));

        assertEquals("Le formulaire ne peut être vide", exception.getMessage());
    }

    @Test
    void update_ok(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(dogRepository.save(any())).thenReturn(dog);

        dogService.update(updateForm, anyLong());
        verify(dogRepository, times(1)).save(any(Dog.class));
    }

    @Test
    void update_ko_dogNotFound(){
        long id = 1L;
        when(dogRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> dogService.update(updateForm, 1L));

        assertEquals("Le chien avec l'ID " + id + " n'existe pas", exception.getMessage());
    }

    @Test
    void upddate_ko_FormNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> dogService.update(null, 1L));

        assertEquals("Le formulaire ne peut être vide", exception.getMessage());
    }

    @Test
    void getOne_ok(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));

        Dog result = dogService.getDogById(1L);

        assertEquals(dog, result);
    }

    @Test
    void getOne_ko_notFound(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> dogService.getDogById(1L));

        assertEquals("Chien pas trouvé", exception.getMessage());
    }

    @Test
    void getAll_ok(){
        when(dogRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(dog));

        List<Dog> dogs = dogService.search(searchForm);
        assertEquals(1, dogs.size());
        assertEquals(dog, dogs.get(0));
    }

    @Test
    void findAllByOwner(){
        when(dogRepository.findAllByOwner_Id(anyLong())).thenReturn(Collections.singletonList(dog));

        List<Dog> dogs = dogService.findAllByOwner(1L);

        assertEquals(1, dogs.size());
        assertEquals(dog, dogs.get(0));
    }

    @Test
    void delete_ok(){
        dogService.delete(1L);

        verify(dogRepository, times(1)).deleteById(eq(1L));
    }
}