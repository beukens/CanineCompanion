package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.MorphologyService;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyDto;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/morphology")
public class MorphologyController {
    private final MorphologyService morphologyService;

    public MorphologyController(MorphologyService morphologyService) {
        this.morphologyService = morphologyService;
    }

    /**
     * Creates a new morphology record using the provided MorphologyForm.
     *
     * @param form The MorphologyForm object containing the details of the new record.
     */
    @PostMapping
    public void create(@RequestBody MorphologyForm form){
        morphologyService.create(form);
    }

    /**
     * Retrieves a single morphology record with the given ID.
     *
     * @param id The ID of the morphology record to retrieve.
     * @return A ResponseEntity containing the MorphologyDto of the retrieved record
     */
    @GetMapping("/{id}")
    public ResponseEntity<MorphologyDto> getOne(@PathVariable long id){
        return ResponseEntity.ok(MorphologyDto.fromEntity(morphologyService.getOne(id)));
    }

    /**
     * Deletes a morphology record identified by the given ID.
     *
     * @param id The ID of the morphology record to delete.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        morphologyService.delete(id);
    }
}
