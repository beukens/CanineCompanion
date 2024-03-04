package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeightServiceImplTest {
    @Mock
    private WeightRepository weightRepository;
    @InjectMocks
    private WeightServiceImpl weightService;

    private Weight weight;
    private Dog dog;

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