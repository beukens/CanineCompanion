package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

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
    void delete_ok(){
        dogService.delete(1L);

        verify(dogRepository, times(1)).deleteById(eq(1L));
    }
}