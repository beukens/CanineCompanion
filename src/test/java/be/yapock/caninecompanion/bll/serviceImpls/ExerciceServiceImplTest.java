package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Exercice;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ExerciceServiceImplTest {
    @Mock
    private ExerciceRepository exerciceRepository;
    @InjectMocks
    private ExerciceServiceImpl exerciceService;

    private Exercice exercice;
    private ExerciceCreateForm form;

    @BeforeEach
    void setUp() {
        exercice = Exercice.builder()
                .name("Test Exercice")
                .description("Test Description")
                .frequencies(2.5)
                .build();
        form = new ExerciceCreateForm("Test Exercice", "Test Description", 2.5);
    }
}