package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreedServiceImplTest {
    @Mock
    private BreedRepository breedRepository;
    @InjectMocks
    private BreedServiceImpl breedService;
    private Breed breed;
    private BreedForm form;
    @BeforeEach
    void setUp() {
        breed = Breed.builder()
                .id(1L)
                .name("name")
                .size(DogSize.BIG)
                .raceGroup(RaceGroup.GREYHOUND)
                .temperament("temperament")
                .build();
        form = new BreedForm(breed.getName(),breed.getRaceGroup(),breed.getSize(),breed.getTemperament());
    }

    @Test
    void create_ok(){
        when(breedRepository.save(any(Breed.class))).thenReturn(breed);

        breedService.create(form);

        verify(breedRepository, times(1)).save(any(Breed.class));
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->breedService.create(null));
        assertEquals("le Formulaire ne peut être vide", exception.getMessage());
    }

    @Test
    void getOne(){
        when(breedRepository.findById(1L)).thenReturn(Optional.of(breed));
        Breed result= breedService.getOne(1L);
        assertEquals(breed, result);
    }

    @Test
    void delete_ok(){
        breedService.delete(1L);
        verify(breedRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAll_ok(){
        when(breedRepository.findAll()).thenReturn(Collections.singletonList(breed));

        List<Breed> result = breedService.getAll();

        assertEquals(Collections.singletonList(breed),result);
    }
}