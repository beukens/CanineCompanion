package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;
import org.springframework.web.bind.annotation.*;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceFullDTO;
import org.springframework.http.ResponseEntity;

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

    /**
     * Retrieves a single Exercice by its ID.
     *
     * @param id The ID of the Exercice to retrieve.
     * @return ResponseEntity containing the ExerciceFullDTO representation of the retrieved Exercice, if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciceFullDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(ExerciceFullDTO.fromEntity(exerciceService.getOneById(id)));
    }

    /**
     * Deletes an exercise by its ID.
     *
     * @param id The ID of the exercise to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteExercice(@PathVariable long id) {
        exerciceService.delete(id);
    }
}
