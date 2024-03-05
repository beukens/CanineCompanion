package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineUpdateForm;
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
    void update_ok(){
        when(vaccineRepository.findById(anyLong())).thenReturn(Optional.of(vaccine));
        when(vaccineRepository.save(any(Vaccine.class))).thenReturn(vaccine);
        vaccineService.update(new VaccineUpdateForm(LocalDate.now()), 1L);
        verify(vaccineRepository, times(1)).save(any());
    }

    @Test
    void update_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> vaccineService.update(null, 1L));
        assertEquals("Le formulaire ne peut être null", exception.getMessage());
    }

    @Test
    void update_ko_vaccineNotFound(){
        when(vaccineRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> vaccineService.update(new VaccineUpdateForm(LocalDate.now()),1L));
        assertEquals("Vaccin pas trouvé", exception.getMessage());
    }
}