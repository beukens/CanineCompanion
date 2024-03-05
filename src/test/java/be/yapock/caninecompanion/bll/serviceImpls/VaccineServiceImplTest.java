package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;
import jakarta.persistence.EntityNotFoundException;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VaccineServiceImplTest {
    @Mock
    private VaccineRepository vaccineRepository;
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private VaccineServiceImpl vaccineService;
    private Vaccine vaccine;
    private Dog dog;
    private VaccineForm form;

    @BeforeEach
    void setUp() {
        dog = Dog.builder()
                .id(1L)
                .build();
        vaccine= Vaccine.builder()
                .id(1L)
                .disease(Disease.LEPTOSPIROSE)
                .lastBooster(LocalDate.now())
                .dog(dog)
                .frequencies(0.5)
                .build();
        form = new VaccineForm(vaccine.getDisease(),vaccine.getLastBooster(),vaccine.getDog().getId());
    }

    @Test
    void create_ok(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(vaccineRepository.save(any(Vaccine.class))).thenReturn(vaccine);
        vaccineService.create(form);
        verify(vaccineRepository,times(1)).save(any(Vaccine.class));
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> vaccineService.create(null));

        assertEquals("Le formulaire ne peut être null", exception.getMessage());
    }

    @Test
    void create_ko_dogNotFound(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()->vaccineService.create(form));
        assertEquals("Chien pas trouvé", exception.getMessage());
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