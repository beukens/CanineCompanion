package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * Retrieves a list of all breeds.
     *
     * @return A ResponseEntity object containing a list of BreedDTO objects representing the breeds.
     */
    @GetMapping
    public ResponseEntity<List<BreedDTO>> getAll(){
        return ResponseEntity.ok(breedService.getAll().stream()
                .map(BreedDTO::fromEntity)
                .toList());
    }
}
