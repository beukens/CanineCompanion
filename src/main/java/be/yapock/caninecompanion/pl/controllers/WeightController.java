package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.pl.models.weight.WeightOneDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weight")
public class WeightController {
    private final WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
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
