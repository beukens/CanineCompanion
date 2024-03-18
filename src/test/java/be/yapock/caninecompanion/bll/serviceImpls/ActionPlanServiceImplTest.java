package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Exercice;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActionPlanServiceImplTest {
    @Mock
    private ActionPlanRepository actionPlanRepository;
    @Mock
    private DogRepository dogRepository;
    @Mock
    private ExerciceRepository exerciceRepository;
    @InjectMocks
    private ActionPlanServiceImpl actionPlanService;

    private ActionPlan actionPlan;
    private ActionPlanForm actionPlanForm;
    private ActionPlanUpdateForm actionPlanUpdateForm;
    private Dog dog;
    private Exercice exercice;

    @BeforeEach
    void setUp() {
        dog = Dog.builder()
                .id(1L)
                .build();
        exercice= Exercice.builder()
                .id(2L)
                .build();
        actionPlanForm = new ActionPlanForm(1L, Collections.singletonList(2L));
        actionPlan = ActionPlan.builder()
                .exercices(Collections.singletonList(exercice))
                .dog(dog)
                .date(LocalDate.now())
                .id(3L)
                .build();
        actionPlanUpdateForm = new ActionPlanUpdateForm(Collections.singletonList(2L));
    }

    @Test
    void create_ok() {
        when(actionPlanRepository.save(any())).thenReturn(actionPlan);
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));
        when(exerciceRepository.findAllById(anyList())).thenReturn(Collections.singletonList(exercice));
        actionPlanService.create(actionPlanForm);
        verify(actionPlanRepository, times(1)).save(any());
    }

    @Test
    void create_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> actionPlanService.create(null));
        assertEquals("Form ne peut être null", exception.getMessage());
    }

    @Test
    void create_ko_dogNotFound(){
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> actionPlanService.create(actionPlanForm));
        assertEquals("Chien pas trouvé", exception.getMessage());
    }

    @Test
    void update_ok() {
        when(actionPlanRepository.findById(anyLong())).thenReturn(Optional.of(actionPlan));
        when(exerciceRepository.findAllById(anyList())).thenReturn(Collections.singletonList(exercice));
        when(actionPlanRepository.save(any(ActionPlan.class))).thenReturn(actionPlan);
        actionPlanService.update(3L, actionPlanUpdateForm);
        verify(actionPlanRepository, times(1)).save(any(ActionPlan.class));
    }

    @Test
    void update_ko_FormNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> actionPlanService.update(3L,null));
        assertEquals("Form ne peut être null", exception.getMessage());
    }

    @Test
    void update_ko_ActionPlanNotFound(){
        when(actionPlanRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> actionPlanService.update(3L, actionPlanUpdateForm));
        assertEquals("Plan d'action pas trouvé", exception.getMessage());
    }

    @Test
    void delete() {
        actionPlanService.delete(anyLong());
        verify(actionPlanRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getOneById() {
        when(actionPlanRepository.findById(anyLong())).thenReturn(Optional.of(actionPlan));
        ActionPlan result = actionPlanService.getOneById(anyLong());
        assertEquals(result, actionPlan);
    }

    @Test
    void getOneById_ko_ActionPlanNotFound(){
        when(actionPlanRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> actionPlanService.getOneById(anyLong()));
        assertEquals("Plan d'action pas trouvé", exception.getMessage());
    }

    @Test
    void getAllByDog_ok(){
        when(actionPlanRepository.findAllByDog_Id(anyLong())).thenReturn(Collections.singletonList(actionPlan));
        List<ActionPlan> result = actionPlanService.getAllByDog(anyLong());
        assertEquals(result, Collections.singletonList(actionPlan));
    }
}