package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
}