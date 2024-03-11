package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Diagnostic;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DiagnosticRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticDTO;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;
import jakarta.persistence.EntityNotFoundException;
import jdk.jshell.Diag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiagnosticServiceImplTest {
    @Mock
    private DiagnosticRepository diagnosticRepository;
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DiagnosticServiceImpl diagnosticService;

    private Diagnostic diagnostic;
    private DiagnosticForm form;
    private Dog dog;


    @BeforeEach
    void setUp() {
        dog = Dog.builder().id(1L).build();
        diagnostic = Diagnostic.builder()
                .dog(dog)
                .tenderness(1)
                .destruct(2)
                .excitation(3)
                .scratchesBruises(4)
                .jumpOnPeople(5)
                .vocalize(1)
                .exploration(2)
                .restPlace(3)
                .separation(4)
                .attachement(5)
                .adaptability(1)
                .date(LocalDate.now())
                .contactWAnimals(2)
                .contactWHumans(3)
                .id(1L)
                .affrayed(4)
                .stayingAlone(5)
                .withOtherAnimals(1)
                .withDogs(2)
                .withStranger(3)
                .withFamiliarHuman(4)
                .submissivePosition(5)
                .build();
        form = new DiagnosticForm(diagnostic.getSubmissivePosition(),diagnostic.getWithFamiliarHuman(), diagnostic.getWithStranger(), diagnostic.getWithDogs(), diagnostic.getWithOtherAnimals(), diagnostic.getStayingAlone(), diagnostic.getAffrayed(), diagnostic.getContactWHumans(), diagnostic.getContactWAnimals(), diagnostic.getAdaptability(), diagnostic.getAttachement(), diagnostic.getSeparation(), diagnostic.getRestPlace(), diagnostic.getAffrayed(), diagnostic.getAttachement(), diagnostic.getContactWHumans(),diagnostic.getJumpOnPeople(), diagnostic.getDestruct(),diagnostic.getScratchesBruises(), diagnostic.getExcitation(),diagnostic.getId());
    }

    @Test
    void getOne_ok(){
        when(diagnosticRepository.findById(anyLong())).thenReturn(Optional.of(diagnostic));

        Diagnostic result = diagnosticService.getOne(1L);
        assertEquals(diagnostic, result);
    }

    @Test
    void getOne_ko(){
        when(diagnosticRepository.findById((anyLong()))).thenReturn(Optional.empty());
        Exception exception= assertThrows(EntityNotFoundException.class, ()-> diagnosticService.getOne(1L));
        assertEquals("Diagnostique pas trouvé", exception.getMessage());
    }

    @Test
    void create_ok(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(diagnosticRepository.save(any(Diagnostic.class))).thenReturn(diagnostic);
        diagnosticService.create(form);
        verify(diagnosticRepository, times(1)).save(any(Diagnostic.class));
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> diagnosticService.create(null));
        assertEquals("Form ne peut être null", exception.getMessage());
    }

    @Test
    void create_ko_dogNotFound(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()->diagnosticService.create(form));
        assertEquals("pas trouvé le woof woof", exception.getMessage());
    }

    @Test
    void getAllByDog(){
        when(diagnosticRepository.findAllByDog_Id(anyLong())).thenReturn(Collections.singletonList(diagnostic));
        List<Diagnostic> result = diagnosticService.getAllByDog(1L);
        assertEquals(Collections.singletonList(diagnostic), result);
    }
}