package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Diagnostic;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DiagnosticRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticDTO;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
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
}