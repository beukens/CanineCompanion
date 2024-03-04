package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BreedServiceImplTest {
    @Mock
    private BreedRepository breedRepository;
    @InjectMocks
    private BreedServiceImpl breedService;
    private Breed breed;
    @BeforeEach
    void setUp() {
        breed = Breed.builder()
                .id(1L)
                .name("name")
                .size(DogSize.BIG)
                .raceGroup(RaceGroup.GREYHOUND)
                .temperament("temperament")
                .build();
    }
}