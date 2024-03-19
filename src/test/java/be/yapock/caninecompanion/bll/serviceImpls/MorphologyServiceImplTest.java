package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Morphology;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.MorphologyRepository;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class MorphologyServiceImplTest {
    @Mock
    private MorphologyRepository morphologyRepository;
    @Mock
    private BreedRepository breedRepository;
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private MorphologyServiceImpl morphologyService;
    private Morphology morphology;
    private MorphologyForm morphologyForm;
    private Breed breed;
    private Dog dog;

    @BeforeEach
    void setUp(){
        dog= Dog.builder()
                .id(1L)
                .build();
        breed = Breed.builder()
                .id(2L)
                .build();
        morphology = Morphology.builder()
                .dog(dog)
                .coat("coat")
                .frontBackProportion(1.1)
                .headMorphology("head morphology")
                .chestPerimeter(1.2)
                .breeds(Collections.singletonList(breed))
                .notes("notes")
                .height(1.3)
                .id(3L)
                .build();
        morphologyForm = new MorphologyForm(morphology.getCoat(), morphology.getHeight(), morphology.getChestPerimeter(), morphology.getFrontBackProportion(), morphology.getHeadMorphology(), morphology.getNotes(), Collections.singletonList(3L), morphology.getDog().getId());
    }

    @Test
    void create_ok() {
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(breedRepository.findAllById(anyCollection())).thenReturn(Collections.singletonList(breed));
        when(morphologyRepository.save(any(Morphology.class))).thenReturn(morphology);

        morphologyService.create(morphologyForm);

        verify(morphologyRepository, times(1)).save(any());
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> morphologyService.create(null));
        assertEquals("Form ne peut être null", exception.getMessage());
    }

    @Test
    void create_ko_dogNotFound(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> morphologyService.create(morphologyForm));
        assertEquals("Chien pas trouvé", exception.getMessage());
    }

    @Test
    void getOne_ok() {
        when(morphologyRepository.findById(anyLong())).thenReturn(Optional.of(morphology));
        Morphology result = morphologyService.getOne(1L);
        assertEquals(morphology, result);
    }

    @Test
    void getOne_ko_morphologyNotFound(){
        when(morphologyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> morphologyService.getOne(1L));
        assertEquals("Morphologie pas trouvée", exception.getMessage());
    }

    @Test
    void delete_ok() {
        morphologyService.delete(3L);
        verify(morphologyRepository, times(1)).deleteById(3L);
    }

}