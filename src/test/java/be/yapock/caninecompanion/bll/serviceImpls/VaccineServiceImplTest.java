package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
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
class VaccineServiceImplTest {
    @Mock
    private VaccineRepository vaccineRepository;
    @InjectMocks
    private VaccineServiceImpl vaccineService;
    private Vaccine vaccine;
    private Dog dog;

    @BeforeEach
    void setUp() {
        dog = new Dog();
        vaccine= Vaccine.builder()
                .id(1L)
                .disease(Disease.LEPTOSPIROSE)
                .lastBooster(LocalDate.now())
                .dog(dog)
                .frequencies(0.5)
                .build();
    }

    @Test
    void getOne_ok(){
        when(vaccineRepository.findById(anyLong())).thenReturn(Optional.of(vaccine));

        Vaccine result = vaccineService.getOne(1L);

        assertEquals(result, vaccine);
    }

    @Test
    void getOne_ko(){
        when(vaccineRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> vaccineService.getOne(1L));
        assertEquals("Vaccin pas trouvé", exception.getMessage());
    }
}