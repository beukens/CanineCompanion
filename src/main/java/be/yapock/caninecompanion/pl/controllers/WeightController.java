package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.pl.models.weight.WeightForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import be.yapock.caninecompanion.pl.models.weight.WeightOneDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import be.yapock.caninecompanion.pl.models.weight.WeightAllDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weight")
public class WeightController {
    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    /**
     * Retrieves a list of WeightAllDTO objects for the given ID.
     *
     * @param id The ID of the weight.
     * @return ResponseEntity containing a list of WeightAllDTO objects representing the weight data.
     */
    @GetMapping("/all/{id}")
    public ResponseEntity<List<WeightAllDTO>> getAll(@PathVariable long id){
        return ResponseEntity.ok(weightService.getAll(id).stream()
                .map(WeightAllDTO::fromEntity)
                .toList());
    }

    /**
     * Creates a new weight entry using the provided WeightForm.
     *
     * @param form the WeightForm object containing the dogId and weight information
     */
    @PostMapping("/")
    public void createWeight(@RequestBody WeightForm form) {
        weightService.create(form);
    }

    /**
     * Retrieves a single Weight entity by its ID.
     *
     * @param id the ID of the Weight entity to retrieve
     * @return a ResponseEntity containing the WeightOneDto representation of the retrieved Weight entity,
     *         or ResponseEntity with an appropriate status code indicating no matching Weight entity was found
     */
    @GetMapping("/{id}")
    public ResponseEntity<WeightOneDto> getOne(@PathVariable long id){
        return ResponseEntity.ok(WeightOneDto.fromEntity(weightService.getOne(id)));
    }
}
