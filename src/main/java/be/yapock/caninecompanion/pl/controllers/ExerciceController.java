package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercice")
public class ExerciceController {
    private final ExerciceService exerciceService;

    public ExerciceController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    /**
     * Creates an exercise using the provided exercise creation form.
     *
     * @param form The exercise creation form containing the exercise details.
     */
    @PostMapping("/")
    public void createExercice(@RequestBody ExerciceCreateForm form) {
        exerciceService.create(form);
    }
}
