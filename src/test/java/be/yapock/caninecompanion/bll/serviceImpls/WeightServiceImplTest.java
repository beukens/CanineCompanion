package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import be.yapock.caninecompanion.pl.models.weight.WeightForm;
import jakarta.persistence.EntityNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeightServiceImplTest {
    @Mock
    private WeightRepository weightRepository;
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private WeightServiceImpl weightService;

    private Weight weight;
    private Dog dog;
    private WeightForm form;

    @BeforeEach
    void setUp() {
        weight = Weight.builder()
                .id(1L)
                .weight(10.5)
                .date(LocalDate.now())
                .dog(dog)
                .build();
        dog = Dog.builder()
                .firstName("firstName")
                .lastName("lastName")
                .dateOfBirth(LocalDate.now())
                .sex("sex")
                .isSterilized(true)
                .build();
        form= new WeightForm(dog.getId(),10.5);
    }

    @Test
    void create_ok(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(weightRepository.save(any(Weight.class))).thenReturn(weight);

        weightService.create(form);

        verify(weightRepository, times(1)).save(any(Weight.class));
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> weightService.create(null));

        assertEquals("le formulaire ne peut être null", exception.getMessage());
    }

    @Test
    void create_ko_dogNotFound() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> weightService.create(form));

        assertEquals("Chien pas trouvé", exception.getMessage());
    }

    @Test
    void getOne_ok(){
        when(weightRepository.findLastByDog_Id(anyLong())).thenReturn(Optional.of(weight));
        Weight result = weightService.getOne(1L);
        assertEquals(weight, result);
    }

    @Test
    void getOne_ko_notFound(){
        when(weightRepository.findLastByDog_Id(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> weightService.getOne(1L));
        assertEquals("Chien pas trouvé", exception.getMessage());
    }
}