package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/breed")
public class BreedController {
    private final BreedService breedService;

    public BreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(BreedDTO.fromEntity(breedService.getOne(id)));
    }

    /**
     * Deletes a Breed by its ID.
     *
     * @param id The ID of the Breed to be deleted.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        breedService.delete(id);
    }
}
