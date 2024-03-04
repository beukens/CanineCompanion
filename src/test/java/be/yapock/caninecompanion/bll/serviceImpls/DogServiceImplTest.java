package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DogServiceImpl dogService;

    private DogCreateForm createForm;
    private Dog dog;
    private DogSearchForm searchForm;

    @BeforeEach
    void setUp(){
        createForm = new DogCreateForm("John", "Doe", LocalDate.now(), "Male", true);
        dog = Dog.builder()
                .firstName(createForm.firstName())
                .lastName(createForm.lastName())
                .dateOfBirth(createForm.dateOfBirth())
                .sex(createForm.sex())
                .isSterilized(createForm.isSterilized())
                .build();
        searchForm= new DogSearchForm("");
    }

    @Test
    void create_ok() {
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
    void getAll_ok(){
        when(dogRepository.findAll(any(Specification.class))).thenReturn(Collections.singletonList(dog));

        List<Dog> dogs = dogService.search(searchForm);
        assertEquals(1, dogs.size());
        assertEquals(dog, dogs.get(0));
    }
}